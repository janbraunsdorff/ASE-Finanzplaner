package de.janbraunsdorff.ase.layer.domain.bank;

public class BankCreateCommand {
    private final String name;
    private final String acronym;
    private final String type;

    public BankCreateCommand(String name, String acronym, String type) {
        this.name = name;
        this.acronym = acronym;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getType() {
        return this.type;
    }
}
