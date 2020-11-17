package de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class AccountMemoryEntity {
    private final String id;
    private final String name;
    private final String number;
    private final Map<Integer, TransactionMemoryEntity> transactions;
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

    public AccountMemoryEntity(Account a) {
        this.id = UUID.randomUUID().toString();
        this.name = a.getName();
        this.number = a.getNumber();
        this.transactions = new HashMap<>();
        this.acronym = a.getAcronym();
    }

    public void addTransaction(TransactionMemoryEntity entity) {
        this.transactions.put(entity.getIndex(), entity);
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

    public Collection<TransactionMemoryEntity> getTransactionEntities() {
        return this.transactions.values();
    }

    public void removeTransaction(Integer acronym) {
        this.transactions.remove(acronym);
    }

    public Account convertToDomain() {
        return new Account(this.name, this.number, new ArrayList<Transaction>(this.transactions.values().stream().map(TransactionMemoryEntity::convertToDomain).collect(Collectors.toList())), this.acronym);
    }
}
