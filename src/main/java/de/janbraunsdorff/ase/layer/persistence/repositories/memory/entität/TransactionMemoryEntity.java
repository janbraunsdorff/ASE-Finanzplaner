package de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class TransactionMemoryEntity {
    private final String id;
    private final Integer value;
    private final LocalDate date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final Integer index;


    // minimal with id
    public TransactionMemoryEntity(String id, Integer value, String thirdParty, String category, Boolean isContract, Integer index) {
        this.id = id;
        this.index = index;
        this.date = LocalDate.now();
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

    public Integer getIndex() {
        return this.index;
    }
}
