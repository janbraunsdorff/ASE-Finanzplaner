package de.janbraunsdorff.ase.layer.persistence.database.entity;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;
import de.janbraunsdorff.ase.layer.domain.contract.data.Interval;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@Table(name = "contract", schema = "public")
@Entity
public class ContractDatabaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "expected_value", nullable = false)
    private Long expectedValue;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "started_at", nullable = false)
    private LocalDate startedAt;

    @Column(name = "finished_at", nullable = false)
    private LocalDate finishedAt;

    @Column(name = "issuer", nullable = false)
    private String issuer;

    @Column(name = "expected_intervall", nullable = false)
    private Integer expectedIntervall;

    @Column(name = "\"interval\"", nullable = false)
    private String interval;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account", nullable = false)
    private AccountDatabaseEntity account;

    public ContractDatabaseEntity(Contract contract, AccountDatabaseEntity account) {
        this.id = contract.getId();
        this.name = contract.getName();
        this.interval = contract.getExpected();
        this.account = account;
        this.startedAt = contract.getStart();
        this.finishedAt = contract.getEnd();
        this.expectedValue = (long) contract.getExpectedValue().getValue();
        //this.expectedIntervall = contract.getInterval()
        this.expectedIntervall = 0;
        this.issuer = contract.getThirdParty();
    }

    public ContractDatabaseEntity() {
    }

    public AccountDatabaseEntity getAccount() {
        return account;
    }

    public void setAccount(AccountDatabaseEntity account) {
        this.account = account;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Integer getExpectedIntervall() {
        return expectedIntervall;
    }

    public void setExpectedIntervall(Integer expectedIntervall) {
        this.expectedIntervall = expectedIntervall;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Long getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Long expectedValue) {
        this.expectedValue = expectedValue;
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

    public Contract toDomain() {
        return new Contract(id, name, interval, account.getAcronym(), startedAt, finishedAt, new Value(expectedValue.intValue()), Collections.emptyList(), Interval.Monthly, issuer);
    }
}