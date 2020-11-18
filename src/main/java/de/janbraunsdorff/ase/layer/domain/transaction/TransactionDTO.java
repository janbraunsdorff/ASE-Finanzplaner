package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;
import java.util.UUID;

public class TransactionDTO {
    private final Integer value;
    private final LocalDate date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final Integer index;

    public TransactionDTO(Integer value, LocalDate date, String thirdParty, String category, Boolean isContract, Integer index) {
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
        this.index = index;
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
        return index;
    }
}
