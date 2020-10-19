package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.io.IOException;
import java.util.List;

public class Repository implements CrudBankRepository {

    private final JsonReader reader;
    private final String bankRepoPath;

    public Repository(String bankRepoPath) {
        this.bankRepoPath = bankRepoPath;
        this.reader = new JsonReader();
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
    public BankEntity create(BankEntity bankEntity) {
        return null;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) {
        return null;
    }

    @Override
    public boolean delete(String bankId) {
        return false;
    }
}
