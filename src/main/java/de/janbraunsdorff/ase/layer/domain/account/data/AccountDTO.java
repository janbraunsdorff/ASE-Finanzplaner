package de.janbraunsdorff.ase.layer.domain.account.data;

import de.janbraunsdorff.ase.layer.domain.Value;

public class AccountDTO {
    private final String name;
    private final String accountNumber;
    private final Integer numberOfTransaction;
    private final String acronym;
    private final Value value;
    private final String bankName;

    public AccountDTO(String name, String bankNumber, Integer numberOfTransaction, String acronym, Value value, String bankName) {
        this.name = name;
        this.accountNumber = bankNumber;
        this.numberOfTransaction = numberOfTransaction;
        this.acronym = acronym;
        this.value = value;
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getNumberOfTransaction() {
        return numberOfTransaction;
    }

    public String getAcronym() {
        return acronym;
    }

    public Value getValue() {
        return this.value;
    }
}
