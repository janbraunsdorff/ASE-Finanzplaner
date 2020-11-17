package de.janbraunsdorff.ase.layer.persistence.repositories.entität;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountEntity {
    private final String id;
    private final String name;
    private final String number;
    private final Map<String, TransactionEntity> transactions;
    private final String acronym;


    public AccountEntity(String name, String number, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.number = number;
        this.acronym = acronym;
        this.transactions = new HashMap<>();
    }

    public AccountEntity(String id, String name, String number, String acronym) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.transactions = new HashMap<>();
        this.acronym = acronym;
    }

    public void addTransaction(TransactionEntity entity) {
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

    public Collection<TransactionEntity> getTransactionEntities(){
        return this.transactions.values();
    }

    public int getAmountOfAccountInCent() {
        return this.transactions.values()
                .stream()
                .map(TransactionEntity::getValue)
                .reduce(0, Integer::sum);
    }

    public void removeTransaction(String acronym) {
        this.transactions.remove(acronym);
    }
}
