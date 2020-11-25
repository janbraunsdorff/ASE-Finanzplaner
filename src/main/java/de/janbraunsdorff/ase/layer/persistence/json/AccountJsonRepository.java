package de.janbraunsdorff.ase.layer.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountJsonRepository implements AccountRepository {

    private final Path path;
    private final Gson gson;

    public AccountJsonRepository(String path) {
        this.path = Paths.get(path + "/account.json");
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("dd-MM-yyyy")
                .create();

    }

    @Override
    public void createAccount(Account account) throws AcronymAlreadyExistsException {
        try {
            checkForFile();
            List<AccountJsonEntity> accountJson = readFile();
            Optional<AccountJsonEntity> first = accountJson
                    .stream()
                    .filter(f -> f.getAcronym().equals(account.getAcronym()))
                    .findFirst();
            if (first.isPresent()) {
                throw new AcronymAlreadyExistsException(account.getAcronym());
            }

            accountJson.add(new AccountJsonEntity(account.getId(), account.getBankAcronym(), account.getName(), account.getNumber(), account.getAcronym()));
            writeFile(accountJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccountByAcronym(String acronym) throws AccountNotFoundException {
        try {
            Optional<AccountJsonEntity> first = readFile().stream().filter(a -> a.getAcronym().equals(acronym)).findFirst();
            if (first.isPresent()) {
                return new Account(first.get().getId(), first.get().getBankAcronym(), first.get().getName(), first.get().getNumber(), first.get().getAcronym());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new AccountNotFoundException(acronym);
    }

    @Override
    public List<Account> getAccountsOfBankByBankAcronym(String bank) throws BankNotFoundException {
        try {
            List<Account> collect = readFile()
                    .stream()
                    .filter(f -> f.getBankAcronym().equals(bank))
                    .map(f -> new Account(f.getId(), f.getBankAcronym(), f.getName(), f.getNumber(), f.getAcronym()))
                    .sorted(Comparator.comparing(Account::getAcronym))
                    .collect(Collectors.toList());
            return collect;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw  new BankNotFoundException(bank);
    }

    @Override
    public void deleteAccountByAcronym(String acronym) throws AccountNotFoundException {
        try {
            List<AccountJsonEntity> collect = readFile().stream().filter(a -> !a.getAcronym().equals(acronym)).collect(Collectors.toList());
            writeFile(collect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<AccountJsonEntity> readFile() throws IOException {
        checkForFile();
        String s = new String(Files.readAllBytes(path));
        ArrayList<AccountJsonEntity> ts = gson.fromJson(s, new TypeToken<List<AccountJsonEntity>>(){}.getType());

        return ts;
    }

    private void checkForFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, "[]".getBytes());
        }
    }

    private void writeFile(List<AccountJsonEntity> list) throws IOException {
        String s1 = gson.toJson(list);
        Files.write(path, s1.getBytes());
    }


}
