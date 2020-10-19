package de.janbraunsdorff.ase.tech.repositories.json;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.util.List;
import java.util.Optional;

public class Repository implements CrudBankRepository {

    private final JsonReader reader;
    private final String bankRepoPath;

    public Repository(String bankRepoPath) {
        this.bankRepoPath = bankRepoPath;
        this.reader = new JsonReader();
    }

    @Override
    public BankEntity get(String id) {
        Optional<BankEntity> first = reader.readBanks(bankRepoPath).stream().filter(s -> s.getId().equals(id)).findFirst();
        return first.orElseGet(BankEntity::new);

    }

    @Override
    public List<BankEntity> get() {
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
