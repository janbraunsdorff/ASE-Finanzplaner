package de.janbraunsdorff.ase.layer.persistence.json.bank;

import com.google.gson.annotations.SerializedName;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;

public class BankJsonEntity {
    @SerializedName("bank_id")
    private final String id;

    @SerializedName("bank_name")
    private final String name;

    @SerializedName("bank_acronym")
    private final String acronym;

    @SerializedName("bank_type")
    private final String type;

    public BankJsonEntity(String id, String name, String acronym, String type) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.type = type;
    }

    public BankJsonEntity(Bank bank){
        this.id = bank.getId();
        this.name = bank.getName();
        this.acronym = bank.getAcronym();
        this.type = bank.getType().getName();
    }

    public Bank convertToBank(){
        return new Bank(id, name, acronym, BankType.getByName(type));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getType() {
        return type;
    }
}
