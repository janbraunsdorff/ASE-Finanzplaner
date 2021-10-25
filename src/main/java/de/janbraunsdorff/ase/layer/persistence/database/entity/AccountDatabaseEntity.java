package de.janbraunsdorff.ase.layer.persistence.database.entity;

import de.janbraunsdorff.ase.layer.domain.account.data.Account;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "account", indexes = {
        @Index(name = "account_acronym_key", columnList = "acronym", unique = true)
}, schema = "public")
@Entity
public class AccountDatabaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "acronym", nullable = false, length = 10)
    private String acronym;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bank", nullable = false)
    private BankDatabaseEntity bank;

    public AccountDatabaseEntity(String id, BankDatabaseEntity bank, String name, String number, String acronym) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.acronym = acronym;
        this.bank = bank;
    }

    public AccountDatabaseEntity() {
    }

    public BankDatabaseEntity getBank() {
        return bank;
    }

    public void setBank(BankDatabaseEntity bank) {
        this.bank = bank;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account toDomain() {
        return new Account(id, bank.getAcronym(),name, number, acronym);
    }
}