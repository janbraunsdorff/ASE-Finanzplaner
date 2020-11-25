package de.janbraunsdorff.ase.layer.persistence.json;

import com.google.gson.annotations.SerializedName;

public class AccountJsonEntity {
    @SerializedName("account_id")
    private  String id;
    @SerializedName("bank_acronym")
    private  String bankAcronym;
    @SerializedName("account_name")
    private  String name;
    @SerializedName("account_number")
    private  String number;
    @SerializedName("account_acronym")
    private  String acronym;

    public AccountJsonEntity(String id, String bankAcronym, String name, String number, String acronym) {
        this.id = id;
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
        this.acronym = acronym;
    }

    public String getId() {
        return id;
    }

    public String getBankAcronym() {
        return bankAcronym;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAcronym() {
        return acronym;
    }
}
