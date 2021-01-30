package de.janbraunsdorff.ase.layer.domain.account.data;

import java.util.UUID;

public class Account {
    private final String id;
    private final String bankAcronym;
    private final String name;
    private final String number;
    private final String acronym;

    public Account(String id, String bankAcronym, String name, String number, String acronym) {
        this.id = id;
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
        this.acronym = acronym;
    }

    public Account(String bankAcronym, String name, String number, String acronym) {
        this.id = UUID.randomUUID().toString();
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
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

    public String getAcronym() {
        return this.acronym;
    }
}
