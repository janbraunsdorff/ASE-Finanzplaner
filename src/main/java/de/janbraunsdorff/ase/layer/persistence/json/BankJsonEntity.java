package de.janbraunsdorff.ase.layer.persistence.json;

import com.google.gson.annotations.SerializedName;

public class BankJsonEntity {
    @SerializedName("bank_id")
    private final String id;

    @SerializedName("bank_name")
    private final String name;

    @SerializedName("bank_acronym")
    private final String acronym;

    public BankJsonEntity(String id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
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
}
