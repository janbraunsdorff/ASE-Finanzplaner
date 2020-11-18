package de.janbraunsdorff.ase.layer.domain.transaction;


import java.time.LocalDate;

public class TransactionCreateCommand {
    private final String accountAcronym;
    private final Integer value;
    private final LocalDate date;
    private final String thirdParty;
    private final String category;
    private final Boolean isContract;

    public TransactionCreateCommand(String accountAcronym, Integer value, LocalDate date, String thirdParty, String category, Boolean isContract) {
        this.accountAcronym = accountAcronym;
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    public String getAccountAcronym() {
        return accountAcronym;
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
}
