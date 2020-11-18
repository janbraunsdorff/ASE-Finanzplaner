package de.janbraunsdorff.ase.layer.domain.bank;

public class BankDTO {
    private final String name;
    private final String acronym;
    private final Integer value;
    private final Integer numberOfAccount;

    public BankDTO(String name, String acronym, Integer value, Integer numberOfAccount) {
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

    public Integer getValue() {
        return value;
    }

    public Integer getNumberOfAccount() {
        return numberOfAccount;
    }
}
