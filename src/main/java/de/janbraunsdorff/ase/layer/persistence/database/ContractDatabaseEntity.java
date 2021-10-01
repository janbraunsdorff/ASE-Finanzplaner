package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;
import de.janbraunsdorff.ase.layer.domain.contract.data.Interval;

@Entity
public class ContractDatabaseEntity {
    @Id
    @Column(name = "contract_id")
    private String id;

    @Column(name = "contract_name")
    private String name;

    @Column(name = "contract_expected")
    private String expected;

    @Column(name = "contract_accountAcronym")
    private String accountAcronym;

    @Column(name = "contract_start")
    private LocalDate start;

    @Column(name = "contract_end")
    private LocalDate end;

    @Column(name = "contract_expectedValue")
    private Integer expectedValue;

    @Column(name = "contract_interval")
    private Interval interval;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> transactions;

    public ContractDatabaseEntity(String id, String name, String expected, String accountAcronym, LocalDate start, LocalDate end, Integer expectedValue, List<String> transactions, Interval interval) {
        this.id = id;
        this.name = name;
        this.expected = expected;
        this.accountAcronym = accountAcronym;
        this.start = start;
        this.end = end;
        this.expectedValue = expectedValue;
        this.transactions = transactions;
        this.interval = interval;
    }

    public ContractDatabaseEntity() {
        transactions = new ArrayList<>();
    }

    public ContractDatabaseEntity(Contract contract) {
        this.id = contract.getId();
        this.name = contract.getName();
        this.accountAcronym = contract.getAccountAcronym();
        this.start = contract.getStart();
        this.end = contract.getEnd();
        this.expectedValue = contract.getExpectedValue().getValue();
        this.transactions = contract.getTransactions();
        this.expected = contract.getExpected();
        this.interval = contract.getInterval();
    }

    public Contract toDomain() {
        return new Contract(id, name, expected, accountAcronym, start, end, new Value(expectedValue), transactions, interval);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountAcronym() {
        return accountAcronym;
    }

    public LocalDate getStart() {
        return start;
    }

    public Integer getExpectedValue() {
        return expectedValue;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public String getExpected() {
        return expected;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Interval getInterval() {
        return interval;
    }
}
