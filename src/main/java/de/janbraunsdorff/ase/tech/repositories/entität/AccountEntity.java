package de.janbraunsdorff.ase.tech.repositories.entität;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

public class AccountEntity {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("number")
    private String number;

    @SerializedName("order")
    private Integer order;

    @SerializedName("transactions")
    private List<TransactionEntity> transactions;

    @SerializedName("transactions")
    private String acronym;


    public AccountEntity(String id, String name, String number, String acronym, Integer order, List<TransactionEntity> transactions) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.order = order;
        this.transactions = transactions;
        this.acronym = acronym;
    }

    public AccountEntity(String name, String number, String acronym, Integer order, List<TransactionEntity> transactions) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.number = number;
        this.order = order;
        this.acronym = acronym;
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Integer getOrder() {
        return order;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public String getAcronym() {
        return this.acronym;
    }
}
