package de.janbraunsdorff.ase.layer.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionJsonRepository implements TransactionRepository {
    private final Path path;
    private final Gson gson;

    public TransactionJsonRepository(String path) {
        this.path = Paths.get(path + "/transaction.json");
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void createTransaction(Transaction entity) {
        try {
            List<TransactionJsonEntity> json = readFile();
            json.add(new TransactionJsonEntity(entity.getId(), entity.getAccountAcronym(), entity.getValue(), entity.getDate(), entity.getThirdParty(), entity.getCategory(), entity.getContract()));
            writeFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getValueOfAccount(String accountId) {
        try {
            return readFile().stream()
                    .filter(f -> f.getAccountAcronym().equals(accountId))
                    .map(TransactionJsonEntity::getValue)
                    .reduce(0, Integer::sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        if (count < 0){
            count = Integer.MAX_VALUE;
        }
        try {
            return readFile()
                    .stream()
                    .filter(f -> f.getAccountAcronym().equals(id))
                    .limit(count)
                    .map(t -> new Transaction(t.getId(), t.getAccountAcronym(), t.getValue(), t.getDate(), t.getThirdParty(), t.getCategory(), t.getContract()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private ArrayList<TransactionJsonEntity> readFile() throws IOException {
        checkForFile();
        String s = new String(Files.readAllBytes(path));
        ArrayList<TransactionJsonEntity> ts = gson.fromJson(s, new TypeToken<List<TransactionJsonEntity>>(){}.getType());

        return ts;
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
