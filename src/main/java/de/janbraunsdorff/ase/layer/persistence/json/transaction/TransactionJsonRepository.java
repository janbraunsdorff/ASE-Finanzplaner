package de.janbraunsdorff.ase.layer.persistence.json.transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionJsonRepository implements TransactionRepository {
    private final Path path;
    private final Gson gson;

    public TransactionJsonRepository(String path) {
        this.path = Paths.get(path + "/transaction.json");
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
    }

    @Override
    public void createTransaction(Transaction entity) {
        try {
            List<TransactionJsonEntity> json = readFile();
            json.add(new TransactionJsonEntity(entity));
            writeFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms) {
        try {
            List<TransactionJsonEntity> transactionJsonEntityStream = readFile().stream()
                    .filter(f -> accountAcronyms.contains(f.getAccountAcronym()))
                    .sorted(Comparator.comparing(TransactionJsonEntity::getDate).reversed())
                    .collect(Collectors.toList());
            List<TransactionJsonEntity> t1 = transactionJsonEntityStream.stream()
                    .filter(f -> f.getDate().isAfter(start))
                    .filter(f -> f.getDate().isBefore(end))
                    .sorted(Comparator.comparing(TransactionJsonEntity::getDate).reversed())
                    .collect(Collectors.toList());

            return t1.stream().map(TransactionJsonEntity::getValue)
                    .reduce(0, Integer::sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getValueOfAccount(String acronym) {
        return getValueOfAccount(LocalDate.MIN, LocalDate.MAX, Set.of(acronym));
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        if (count < 0) {
            count = Integer.MAX_VALUE;
        }
        try {
            return readFile()
                    .stream()
                    .filter(f -> f.getAccountAcronym().equals(id))
                    .sorted(Comparator.comparing(TransactionJsonEntity::getDate).reversed())
                    .limit(count)
                    .map(TransactionJsonEntity::convertToTransaction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Transaction> deleteTransactionById(String id) throws TransactionNotFoundException {
        try {
            ArrayList<TransactionJsonEntity> transactionJsonEntities = readFile();
            Optional<Transaction> transaction = transactionJsonEntities
                    .stream()
                    .filter(f -> f.getId().equals(id))
                    .map(TransactionJsonEntity::convertToTransaction)
                    .findFirst();
            List<TransactionJsonEntity> collect = transactionJsonEntities
                    .stream()
                    .filter(f -> !f.getId().equals(id))
                    .collect(Collectors.toList());
            writeFile(collect);
            if (transaction.isPresent()){
                return transaction;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new TransactionNotFoundException(id);
    }

    @Override
    public List<Transaction> getTransactionOfAccount(List<String> account, LocalDate start, LocalDate end) {
        try {
            return readFile()
                    .stream()
                    .filter(f -> account.contains(f.getAccountAcronym()))
                    .filter(f -> start.compareTo(f.getDate()) * f.getDate().compareTo(end) >= 0)
                    .sorted(Comparator.comparing(TransactionJsonEntity::getDate).reversed())
                    .map(TransactionJsonEntity::convertToTransaction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Long count(String acronym) {
        try {
            return readFile()
                    .stream()
                    .filter(f -> f.getAccountAcronym().equals(acronym))
                    .count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public List<Transaction> getTransactions(LocalDate start, LocalDate end) {
        try {
            return readFile()
                    .stream()
                    .filter(f -> start.compareTo(f.getDate()) * f.getDate().compareTo(end) >= 0)
                    .map(TransactionJsonEntity::convertToTransaction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Transaction> getLastTransactions(int number) {
        try {
            return readFile()
                    .stream()
                    .sorted(Comparator.comparing(TransactionJsonEntity::getDate).reversed())
                    .limit(number)
                    .map(TransactionJsonEntity::convertToTransaction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public int getMaxValueOfAccount(String accountAcronym) {
        try {
            List<TransactionJsonEntity> collect = readFile()
                    .stream()
                    .filter(t -> t.getAccountAcronym().equals(accountAcronym))
                    .sorted(Comparator.comparing(TransactionJsonEntity::getDate))
                    .collect(Collectors.toList());

            var maxValue = 0;
            var accountValue = 0;

            for (int i = 0; i < collect.size(); i++) {
                TransactionJsonEntity t = collect.get(i);
                accountValue += t.getValue();
                if (i+1 < collect.size() && t.getDate().isEqual(collect.get(i+1).getDate())) {
                    continue;
                }

                if (maxValue < accountValue) {
                    maxValue = accountValue;
                }


            }

            return maxValue;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private ArrayList<TransactionJsonEntity> readFile() throws IOException {
        checkForFile();
        String s = new String(Files.readAllBytes(path));
        return gson.fromJson(s, new TypeToken<List<TransactionJsonEntity>>() {
        }.getType());
    }

    private void checkForFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, "[]".getBytes());
        }
    }

    private void writeFile(List<TransactionJsonEntity> list) throws IOException {
        String s1 = gson.toJson(list);
        Files.write(path, s1.getBytes());
    }
}
