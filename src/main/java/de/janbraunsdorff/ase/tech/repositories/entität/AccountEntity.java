package de.janbraunsdorff.ase.tech.repositories.entität;

import com.google.gson.annotations.SerializedName;
import de.janbraunsdorff.ase.tech.repositories.entität.TransactionEntity;

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

    public AccountEntity() {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
