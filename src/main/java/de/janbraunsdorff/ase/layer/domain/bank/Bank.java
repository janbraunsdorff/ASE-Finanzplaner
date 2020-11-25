package de.janbraunsdorff.ase.layer.domain.bank;


import java.util.UUID;

public class Bank {
    private final String id;
    private final String name;
    private final String acronym;

    public Bank(String id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
    }

    public Bank(String name, String acronym) {
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

}
