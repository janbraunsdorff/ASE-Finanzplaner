package de.janbraunsdorff.ase.layer.domain.account.data;

import de.janbraunsdorff.ase.layer.domain.Value;

public class AccountDTO {
    private final String name;
    private final String accountNumber;
    private final Integer numberOfTransaction;
    private final String acronym;
    private final Value value;
    private final String bankName;
    private final String id;

    public AccountDTO(Account a, int numberOfTransactions, int valueOfAccount, String bankName){
        this.name = a.getName();
        this.accountNumber = a.getNumber();
        this.numberOfTransaction = numberOfTransactions;
        this.acronym = a.getAcronym();
        this.value = new Value(valueOfAccount);
        this.bankName = bankName;
        this.id = a.getId();
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

    public String getId() {
        return this.id;
    }
}
