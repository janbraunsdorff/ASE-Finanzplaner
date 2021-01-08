package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.Value;

import java.time.LocalDate;

public class TransactionDTO {
    private final Value value;
    private final LocalDate date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final String id;
    private final String account;

    public TransactionDTO(Value value, LocalDate date, String thirdParty, String category, Boolean isContract, String id, String account) {
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
        this.id = id;
        this.account = account;
    }

    public TransactionDTO(Transaction transaction){
        this.value = new Value(transaction.getValue());
        this.date = transaction.getDate();
        this.thirdParty = transaction.getThirdParty();
        this.category = transaction.getCategory();
        this.isContract = transaction.getContract();
        this.id = transaction.getId();
        this.account = transaction.getAccountAcronym();
    }

    public Value getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getContract() {
        return isContract;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }
}
