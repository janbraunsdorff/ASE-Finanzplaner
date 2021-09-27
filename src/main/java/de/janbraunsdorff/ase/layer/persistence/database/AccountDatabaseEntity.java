package de.janbraunsdorff.ase.layer.persistence.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import de.janbraunsdorff.ase.layer.domain.account.data.Account;

@Entity
public class AccountDatabaseEntity {
    @Id
    @Column(name = "account_id")
    private String id;
    @Column(name = "bank_acronym")
    private String bankAcronym;
    @Column(name = "account_name")
    private String name;
    @Column(name = "account_number")
    private String number;
    @Column(name = "account_acronym", unique = true)
    private String acronym;

    public AccountDatabaseEntity(String id, String bankAcronym, String name, String number, String acronym) {
        this.id = id;
        this.bankAcronym = bankAcronym;
        this.name = name;
        this.number = number;
        this.acronym = acronym;
    }

    public AccountDatabaseEntity (Account account){
        this.id = account.getId();
        this.bankAcronym = account.getBankAcronym();
        this.name = account.getName();
        this.number = account.getNumber();
        this.acronym = account.getAcronym();
    }

    public AccountDatabaseEntity() {

    }

    public String getId() {
        return id;
    }

    public String getBankAcronym() {
        return bankAcronym;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAcronym() {
        return acronym;
    }

    public Account toDomain() {
        return new Account(id, bankAcronym, name, number, acronym);
    }
}
