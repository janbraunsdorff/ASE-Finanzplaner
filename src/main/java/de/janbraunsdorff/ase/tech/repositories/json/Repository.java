package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Repository implements CrudBankRepository {

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
        return reader.readBanks(bankRepoPath)
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseGet(BankEntity::new);
    }

    @Override
    public List<BankEntity> get() throws IOException {
        return reader.readBanks(this.bankRepoPath);
    }

    @Override
    public BankEntity create(BankEntity bankEntity) throws Exception {
        this.writer.write(bankEntity, this.bankRepoPath);

        return null;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws IOException {
        List<BankEntity> banks = this.reader.readBanks(bankRepoPath)
                .stream()
                .filter(s -> !(s.getId().equals(bankEntity.getId())))
                .collect(Collectors.toList());

        banks.add(bankEntity);
        this.writer.update(banks, bankRepoPath);
        return  bankEntity;

    }

    @Override
    public boolean delete(String bankId) {
        return false;
    }
}
