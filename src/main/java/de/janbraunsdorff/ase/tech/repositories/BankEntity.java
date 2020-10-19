package de.janbraunsdorff.ase.tech.repositories;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankEntity {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("accounts")
    private List<AccountEntity> accounts;


    public BankEntity(String id, String name, List<AccountEntity> accounts) {
        this.id = id;
        this.name = name;
        this.accounts = accounts;
    }

    public BankEntity() {
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

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }
}
