package de.janbraunsdorff.ase.layer.domain.account;

public class AccountCreateCommand {
    private final String bank;
    private final String name;
    private final String number;
    private final String acronym;

    public AccountCreateCommand(String bank, String name, String number, String acronym) {
        this.bank = bank;
        this.name = name;
        this.number = number;
        this.acronym = acronym;
    }

    public String getBank() {
        return bank;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAcronym() {
        return acronym;
    }
}
