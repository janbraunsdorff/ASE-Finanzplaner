package de.janbraunsdorff.ase.tech.repositories.entität;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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


    public AccountEntity(String id, String name, String number, Integer order, List<TransactionEntity> transactions) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.order = order;
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

}
