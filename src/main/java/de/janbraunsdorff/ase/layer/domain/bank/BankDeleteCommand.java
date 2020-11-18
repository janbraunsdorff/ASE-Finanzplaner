package de.janbraunsdorff.ase.layer.domain.bank;

public class BankDeleteCommand {
    private final String acronym;

    public BankDeleteCommand(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }
}
