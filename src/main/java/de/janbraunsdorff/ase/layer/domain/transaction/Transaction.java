package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final String accountAcronym;
    private final Integer value;
    private final LocalDate date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final String ContractName;

    public Transaction(String id, String accountAcronym, Integer value, LocalDate date, String thirdParty, String category, Boolean isContract, String contractName) {
        this.id = id;
        this.accountAcronym = accountAcronym;
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
        ContractName = contractName;
    }

    public Transaction(String accountId, Integer value, LocalDate date, String thirdParty, String category, Boolean isContract, String contractName) {
        ContractName = contractName;
        this.id = UUID.randomUUID().toString();
        this.accountAcronym = accountId;
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    public String getId() {
        return id;
    }

    public String getAccountAcronym() {
        return accountAcronym;
    }

    public Integer getValue() {
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

    public String getContractName() {
        return ContractName;
    }
}
