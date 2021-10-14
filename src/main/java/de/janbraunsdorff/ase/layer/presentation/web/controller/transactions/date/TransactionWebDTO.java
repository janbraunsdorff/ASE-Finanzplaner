package de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

import java.time.format.DateTimeFormatter;

public class TransactionWebDTO {

    private String thirdParty;
    private String date;
    private String amount;
    private String category;
    private Boolean isPositive;
    private Boolean isContract;
    private String account;
    private String id;
    private String contractName;

    public TransactionWebDTO(String thirdParty, String date, String amount, String category, Boolean isPositive, Boolean isContract, String account, String id, String contractName) {
        this.thirdParty = thirdParty;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.isPositive = isPositive;
        this.isContract = isContract;
        this.account = account;
        this.id = id;
        this.contractName = contractName;
    }

    public TransactionWebDTO(TransactionDTO t) {
        this.thirdParty = t.getThirdParty();
        this.date = t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.amount = t.getValue().getFormatted();
        this.category = t.getCategory();
        this.isPositive = t.getValue().isPositive();
        this.isContract = t.getContract();
        this.account = t.getAccount();
        this.id = t.getId();
        this.contractName = t.getContractName();
    }

    public TransactionWebDTO() {
    }


    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getPositive() {
        return isPositive;
    }

    public void setPositive(Boolean positive) {
        isPositive = positive;
    }

    public Boolean getContract() {
        return isContract;
    }

    public void setContract(Boolean contract) {
        isContract = contract;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
