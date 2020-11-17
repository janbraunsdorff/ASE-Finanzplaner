package de.janbraunsdorff.ase.layer.persistence.repositories.entität;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TransactionEntity {
    private final String id;
    private final Integer value;
    private final Date date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;


    // minimal with id
    public TransactionEntity(String id, Integer value, String thirdParty, String category, Boolean isContract) {
        this.id = id;
        this.date = Calendar.getInstance().getTime();
        this.value = value;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    // minimal
    public TransactionEntity(Integer value, String thirdParty, String category, Boolean isContract) {
        this.id = UUID.randomUUID().toString();
        this.date = Calendar.getInstance().getTime();
        this.value = value;
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
