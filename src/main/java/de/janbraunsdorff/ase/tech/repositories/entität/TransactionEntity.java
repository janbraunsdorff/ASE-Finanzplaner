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

    public TransactionEntity(String id, Integer value, Date date, String form, String to, String category, Boolean isContract) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.form = form;
        this.to = to;
        this.category = category;
        this.isContract = isContract;
    }

    public TransactionEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getContract() {
        return isContract;
    }

    public void setContract(Boolean contract) {
        isContract = contract;
    }
}
