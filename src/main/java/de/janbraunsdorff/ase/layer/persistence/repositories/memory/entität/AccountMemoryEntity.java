package de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountMemoryEntity {
    private final String id;
    private final String name;
    private final String number;
    private final Map<String, TransactionMemoryEntity> transactions;
    private final String acronym;


    public AccountMemoryEntity(String name, String number, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.number = number;
        this.acronym = acronym;
        this.transactions = new HashMap<>();
    }

    public AccountMemoryEntity(String id, String name, String number, String acronym) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.transactions = new HashMap<>();
        this.acronym = acronym;
    }

    public void addTransaction(TransactionMemoryEntity entity) {
        this.transactions.put(entity.getId(), entity);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAcronym() {
        return this.acronym;
    }

    public Collection<TransactionMemoryEntity> getTransactionEntities(){
        return this.transactions.values();
    }

    public int getAmountOfAccountInCent() {
        return this.transactions.values()
                .stream()
                .map(TransactionMemoryEntity::getValue)
                .reduce(0, Integer::sum);
    }

    public void removeTransaction(String acronym) {
        this.transactions.remove(acronym);
    }

    public  Account convertToDomain() {
        return new Account();
    }
}
