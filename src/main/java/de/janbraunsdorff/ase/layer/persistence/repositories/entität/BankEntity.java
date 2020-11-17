package de.janbraunsdorff.ase.layer.persistence.repositories.entität;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BankEntity {
    private final String id;
    private final String name;
    private final Map<String, AccountEntity> accounts;
    private final String acronym;


    public BankEntity(String name, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.accounts = new HashMap<>();
        this.acronym = acronym;
    }

    public BankEntity(String id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.accounts = new HashMap<>();
        this.acronym = acronym;
    }

    public void addAccount(AccountEntity entity) {
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

    public Collection<AccountEntity> getAccounts() {
        return this.accounts.values();
    }

    public void removeAccount(String id) {
        this.accounts.remove(id);
    }

    public int getAmountOfAccountsInCent() {
        return this.accounts.values()
                .stream()
                .map(AccountEntity::getAmountOfAccountInCent)
                .reduce(0, Integer::sum);
    }
}
