package de.janbraunsdorff.ase.layer.persistence.json;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class TransactionJsonEntity {
    @SerializedName("transaction_id")
    private String id;
    private String accountAcronym;
    @SerializedName("transaction_value")
    private Integer value;
    @SerializedName("transaction_date")
    private LocalDate date;
    @SerializedName("transaction_thirdParty")
    private String thirdParty;
    @SerializedName("transaction_category")
    private String category;
    @SerializedName("transaction_contract")
    private Boolean isContract;

    public TransactionJsonEntity(String id, String accountAcronym, Integer value, LocalDate date, String thirdParty, String category, Boolean isContract) {
        this.id = id;
        this.accountAcronym = accountAcronym;
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
}
