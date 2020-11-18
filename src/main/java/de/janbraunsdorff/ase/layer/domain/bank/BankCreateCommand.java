package de.janbraunsdorff.ase.layer.domain.bank;

public class BankCreateCommand {
    private final String name;
    private final String acronym;

    public BankCreateCommand(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }
}
