package de.janbraunsdorff.ase.layer.domain.bank;


import java.util.UUID;

public class Bank {
    private final String id;
    private final String name;
    private final String acronym;
    private final BankType type;

    public Bank(String id, String name, String acronym, BankType type) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.type = type;
    }

    public Bank(String name, String acronym, BankType type) {
        this.type = type;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.acronym = acronym;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getAcronym() {
        return this.acronym;
    }

    public BankType getType() {
        return this.type;
    }
}
