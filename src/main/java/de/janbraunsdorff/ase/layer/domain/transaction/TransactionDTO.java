package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;

public class TransactionDTO {
    private final Integer value;
    private final LocalDate date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;
    private final String id;
    private final String account;

    public TransactionDTO(Integer value, LocalDate date, String thirdParty, String category, Boolean isContract, String id, String account) {
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
        this.id = id;
        this.account = account;
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

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }
}
