package de.janbraunsdorff.ase.layer.domain.bank;

import de.janbraunsdorff.ase.layer.domain.Value;

public class BankDTO {
    private final String name;
    private final String acronym;
    private final Value value;
    private final Integer numberOfAccount;

    public BankDTO(String name, String acronym, Value value, Integer numberOfAccount) {
        this.name = name;
        this.acronym = acronym;
        this.value = value;
        this.numberOfAccount = numberOfAccount;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Value getValue() {
        return value;
    }

    public Integer getNumberOfAccount() {
        return numberOfAccount;
    }
}
