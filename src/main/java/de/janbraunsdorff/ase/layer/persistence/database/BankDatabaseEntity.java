package de.janbraunsdorff.ase.layer.persistence.database;

import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankDatabaseEntity {
    @Id
    @Column(name ="bank_id")
    private String id;

    @Column(name ="bank_name")
    private String name;

    @Column(name ="bank_acronym", unique = true)
    private String acronym;

    @Column(name ="bank_type")
    private String type;

    public BankDatabaseEntity(String id, String name, String acronym, String type) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.type = type;
    }

    public BankDatabaseEntity() {
    }

    public Bank toDomain(){
        return new Bank(id, name, acronym, BankType.getByName(type));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getType() {
        return type;
    }
}
