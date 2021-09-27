package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;

@Entity
public class TransactionDatabaseEntity {
    @Id
    @Column(name ="transaction_id")
    private String id;
    @Column(name ="account_Acronym")
    private String accountAcronym;
    @Column(name ="transaction_value")
    private Integer value;
    @Column(name ="transaction_date")
    private LocalDate date;
    @Column(name ="transaction_thirdParty")
    private String thirdParty;
    @Column(name ="transaction_category")
    private String category;
    @Column(name ="transaction_contract")
    private Boolean isContract;

    public TransactionDatabaseEntity(String id, String accountAcronym, Integer value, LocalDate date, String thirdParty, String category, Boolean isContract) {
        this.id = id;
        this.accountAcronym = accountAcronym;
        this.value = value;
        this.date = date;
        this.thirdParty = thirdParty;
        this.category = category;
        this.isContract = isContract;
    }

    public TransactionDatabaseEntity() {
    }

    public Transaction toDomain(){
        return new Transaction(id, accountAcronym, value, date, thirdParty, category, isContract);
    }

    public String getId() {
        return id;
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
