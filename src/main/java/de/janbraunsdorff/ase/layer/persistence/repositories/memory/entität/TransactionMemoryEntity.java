package de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TransactionMemoryEntity {
    private final String id;
    private final Integer value;
    private final Date date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final Integer index;


    // minimal with id
    public TransactionMemoryEntity(String id, Integer value, String thirdParty, String category, Boolean isContract, Integer index) {
        this.id = id;
        this.index = index;
        this.date = Calendar.getInstance().getTime();
        this.value = value;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    // minimal
    public TransactionMemoryEntity(Integer value, String thirdParty, String category, Boolean isContract, Integer index) {
        this.index = index;
        this.id = UUID.randomUUID().toString();
        this.date = Calendar.getInstance().getTime();
        this.value = value;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    public TransactionMemoryEntity(Transaction t, Integer index) {
        this.index = index;
        this.id = UUID.randomUUID().toString();
        this.value = t.getValue();
        this.date = t.getDate();
        this.thirdParty = t.getThirdParty();
        this.category = t.getCategory();
        this.isContract = t.getContract();
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

    public Transaction convertToDomain() {
        return new Transaction(this.value, this.date, this.thirdParty, this.category, this.isContract, this.index);
    }

    public Integer getIndex() {
        return this.index;
    }
}
