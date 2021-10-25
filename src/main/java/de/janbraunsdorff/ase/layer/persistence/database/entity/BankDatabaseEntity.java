package de.janbraunsdorff.ase.layer.persistence.database.entity;

import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "bank", indexes = {
        @Index(name = "bank_acronym_key", columnList = "acronym", unique = true)
}, schema = "public")
@Entity
public class BankDatabaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "acronym", nullable = false, length = 10)
    private String acronym;

    @Column(name = "type")
    private String type;

    @Column(name = "created")
    private LocalDate created;

    public BankDatabaseEntity(String id, String name, String acronym, String type) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.type = type;
    }

    public BankDatabaseEntity() {
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
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

    public Bank toDomain(){
        return new Bank(id, name, acronym, BankType.getByName(type));
    }
}