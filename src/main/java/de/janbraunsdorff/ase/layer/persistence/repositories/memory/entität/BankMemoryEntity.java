package de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität;

import java.util.*;

public class BankMemoryEntity {
    private final String id;
    private final String name;
    private final Map<String, AccountMemoryEntity> accounts;
    private final String acronym;

    public BankMemoryEntity(String id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.accounts = new HashMap<>();
        this.acronym = acronym;
    }

    public void addAccount(AccountMemoryEntity entity) {
        this.accounts.put(entity.getId(), entity);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Collection<AccountMemoryEntity> getAccounts() {
        return this.accounts.values();
    }

    public void removeAccount(String id) {
        this.accounts.remove(id);
    }
}
