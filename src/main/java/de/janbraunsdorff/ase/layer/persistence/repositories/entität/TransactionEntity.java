package de.janbraunsdorff.ase.layer.persistence.repositories.entität;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TransactionEntity {
    private String id;
    private Integer value;
    private Date date;
    private String form;
    private String to;
    private String category;
    private Boolean isContract;

    public TransactionEntity(Integer value, Date date, String form, String to, String category, Boolean isContract) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.date = date;
        this.form = form;
        this.to = to;
        this.category = category;
        this.isContract = isContract;
    }

    public TransactionEntity(Integer value, String form, String to, String category, Boolean isContract) {
        this.id = UUID.randomUUID().toString();
        this.date = Calendar.getInstance().getTime();
        this.value = value;
        this.form = form;
        this.to = to;
        this.category = category;
        this.isContract = isContract;
    }

    public String getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }


}
