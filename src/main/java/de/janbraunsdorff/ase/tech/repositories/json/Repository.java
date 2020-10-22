package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Repository implements CrudBankRepository {
// TODO: Check, if acronym is present twice
    private final JsonReader reader;
    private final JsonWriter writer;
    private final String bankRepoPath;

    public Repository(String bankRepoPath) {
        this.bankRepoPath = bankRepoPath;
        this.reader = new JsonReader();
        this.writer = new JsonWriter(this.reader);
    }


    @Override
    public BankEntity get(String id) throws IOException {
        return this.reader.readBanks(bankRepoPath)
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseGet(BankEntity::new);
    }

    @Override
    public List<BankEntity> get() throws IOException {
        return this.reader.readBanks(this.bankRepoPath);
    }

    @Override
    public BankEntity create(BankEntity bankEntity) throws Exception {
        if (checkForMissingId(bankEntity)) {
            bankEntity.setId(UUID.randomUUID().toString());
        }

        this.writer.write(bankEntity, this.bankRepoPath);
        return bankEntity;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws IOException {
        List<BankEntity> bankEntities = this.reader.readBanks(this.bankRepoPath);
        int len = bankEntities.size();

        List<BankEntity> banks = bankEntities
                .stream()
                .filter(s -> !(s.getId().equals(bankEntity.getId())))
                .collect(Collectors.toList());

        if (len == banks.size()){
            return bankEntity;
        }

        banks.add(bankEntity);
        this.writer.update(banks, this.bankRepoPath);
        return  bankEntity;

    }

    @Override
    public boolean delete(String bankId) throws IOException {
        List<BankEntity> bankEntities = this.reader
                .readBanks(this.bankRepoPath)
                .stream()
                .filter(s -> !(s.getId().equals(bankId)))
                .collect(Collectors.toList());
        this.writer.update(bankEntities, this.bankRepoPath);
        return true;
    }


    private boolean checkForMissingId(BankEntity bankEntity) {
        return bankEntity.getId() == null || bankEntity.getId().isEmpty();
    }
}
