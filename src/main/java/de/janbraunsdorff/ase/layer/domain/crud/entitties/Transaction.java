package de.janbraunsdorff.ase.layer.domain.crud.entitties;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final Integer value;
    private final Date date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;

    public Transaction(String id, Integer value, Date date, String thirdParty, String category, Boolean isContract) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    public Transaction(Integer value, Date date, String thirdParty, String category, Boolean isContract) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
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
