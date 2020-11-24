package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private final String id;
    private final String bankAcronym;
    private final String name;
    private final String number;
    private final List<Transaction> transactions;
    private final String acronym;

    public Account(String bankAcronym, String name, String number, List<Transaction> transactions, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
        this.transactions = transactions;
        this.acronym = acronym;
    }

    public Account(String bankAcronym, String name, String number, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
        this.transactions = new ArrayList<>();
        this.acronym = acronym;
    }

    public String getId() {
        return id;
    }

    public String getBankAcronym() {
        return bankAcronym;
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
}
