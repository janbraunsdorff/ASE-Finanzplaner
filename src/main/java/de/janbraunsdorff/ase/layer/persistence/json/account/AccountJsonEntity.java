package de.janbraunsdorff.ase.layer.persistence.json.account;

import com.google.gson.annotations.SerializedName;
import de.janbraunsdorff.ase.layer.domain.account.Account;

public class AccountJsonEntity {
    @SerializedName("account_id")
    private final String id;
    @SerializedName("bank_acronym")
    private final String bankAcronym;
    @SerializedName("account_name")
    private final String name;
    @SerializedName("account_number")
    private final String number;
    @SerializedName("account_acronym")
    private final String acronym;

    public AccountJsonEntity(String id, String bankAcronym, String name, String number, String acronym) {
        this.id = id;
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
        this.acronym = acronym;
    }

    public AccountJsonEntity (Account account){
        this.id = account.getId();
        this.bankAcronym = account.getBankAcronym();
        this.name = account.getName();
        this.number = account.getNumber();
        this.acronym = account.getAcronym();
    }

    public Account convertToAccount(){
        return new Account(id, bankAcronym, name, number, acronym);
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
