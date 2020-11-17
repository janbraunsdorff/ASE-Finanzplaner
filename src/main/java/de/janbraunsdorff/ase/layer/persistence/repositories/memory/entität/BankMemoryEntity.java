package de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;

import java.util.*;
import java.util.stream.Collectors;


public class BankMemoryEntity {
    private final String id;
    private final String name;
    private final Map<String, AccountMemoryEntity> accounts;
    private final String acronym;


    public BankMemoryEntity(String name, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.accounts = new HashMap<>();
        this.acronym = acronym;
    }

    public BankMemoryEntity(String id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.accounts = new HashMap<>();
        this.acronym = acronym;
    }

    public BankMemoryEntity(Bank bankEntity) {
        this.id = UUID.randomUUID().toString();
        this.name = bankEntity.getName();
        this.accounts = new HashMap<>();
        this.acronym = bankEntity.getAcronym();
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

    public Bank convertToDomainEntity(){
        return new Bank(this.name, new ArrayList<>(this.accounts.values().stream().map(AccountMemoryEntity::convertToDomain).collect(Collectors.toList())), this.acronym);
    }
}
