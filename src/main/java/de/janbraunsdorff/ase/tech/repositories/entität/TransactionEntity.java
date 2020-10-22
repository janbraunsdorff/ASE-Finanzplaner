package de.janbraunsdorff.ase.tech.repositories.entität;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TransactionEntity {
    @SerializedName("id")
    private String id;

    @SerializedName("value")
    private Integer value;

    @SerializedName("date")
    private Date date;

    @SerializedName("from")
    private String form;

    @SerializedName("to")
    private String to;

    @SerializedName("cat")
    private String category;

    @SerializedName("is_contract")
    private Boolean isContract;

    public String getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public String getForm() {
        return form;
    }

    public String getTo() {
        return to;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getContract() {
        return isContract;
    }

}
