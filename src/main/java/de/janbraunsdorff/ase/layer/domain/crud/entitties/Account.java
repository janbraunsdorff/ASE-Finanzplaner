package de.janbraunsdorff.ase.layer.domain.crud.entitties;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String name;
    private final String number;
    private final List<Transaction> transactions;
    private final String acronym;

    public Account(String name, String number, List<Transaction> transactions, String acronym) {
        this.name = name;
        this.number = number;
        this.transactions = transactions;
        this.acronym = acronym;
    }

    public Account(String name, String number, String acronym) {
        this.name = name;
        this.number = number;
        this.transactions = new ArrayList<>();
        this.acronym = acronym;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public String getAcronym() {
        return this.acronym;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public int getAmountOfAccountInCent() {
        return this.transactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();
    }
}
