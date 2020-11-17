package de.janbraunsdorff.ase.layer.domain.crud.entitties;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private final Integer value;
    private final Date date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final Integer index;

    public Transaction(Integer value, Date date, String thirdParty, String category, Boolean isContract, Integer index) {
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
        this.index = index;
    }

    public Transaction(Integer value, String thirdParty, String category, Boolean isContract, Integer index) {
        this.value = value;
        this.date = Calendar.getInstance().getTime();
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
        this.index = index;
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

    public Integer getIndex() {
        return index;
    }
}
