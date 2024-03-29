package de.janbraunsdorff.ase.layer.persistence.json.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;

public class BankJsonRepository implements BankRepository {

    private final Path path;
    private final Gson gson;

    public BankJsonRepository(String path) {
        this.path = Paths.get(path + "/bank.json");
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public List<Bank> getBank() {
        try {
            ArrayList<BankJsonEntity> bankJsonEntities = readFile();
            return readFile()
                    .stream()
                    .map(BankJsonEntity::convertToBank)
                    .sorted(Comparator.comparing(Bank::getName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Bank getBankByAcronym(String acronym) throws BankNotFoundException {
        try {
            Optional<BankJsonEntity> first = readFile()
                    .stream()
                    .filter(f -> f.getAcronym().equals(acronym))
                    .findFirst();
            if (first.isPresent()) {
                return first.get().convertToBank();
            }

        } catch (IOException e) {
        }
        throw new BankNotFoundException(acronym);
    }


    @Override
    public void createBank(Bank bankEntity) throws AcronymAlreadyExistsException {
        try{
            List<BankJsonEntity> jsonEntities = readFile();
            Optional<BankJsonEntity> bankJson = jsonEntities
                    .stream()
                    .filter(f -> f.getAcronym().equals(bankEntity.getAcronym()))
                    .findFirst();
            if (bankJson.isPresent()) {
                throw new AcronymAlreadyExistsException(bankEntity.getAcronym());
            }

            BankJsonEntity entity = new BankJsonEntity(bankEntity);
            jsonEntities.add(entity);
            writeFile(jsonEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBankByAcronym(String bankId) {
        try {
            List<BankJsonEntity> collect = readFile()
                    .stream()
                    .filter(f -> !f.getAcronym().equals(bankId))
                    .collect(Collectors.toList());
            writeFile(collect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<BankJsonEntity> readFile() throws IOException {
        checkForFile();
        String s = new String(Files.readAllBytes(path));

        return gson.fromJson(s, new TypeToken<List<BankJsonEntity>>() {
        }.getType());
    }

    void checkForFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, "[]".getBytes());
        }
    }

    void writeFile(List<BankJsonEntity> list) throws IOException {
        String s1 = gson.toJson(list);
        Files.write(path, s1.getBytes());
    }
}
