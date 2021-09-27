package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;

@Entity
public class ContractDatabaseEntity {
    @Id
    @Column(name = "contract_id")
    private String id;

    @Column(name = "contract_name")
    private String name;

    @Column(name = "contract_accountAcronym")
    private String accountAcronym;

    @Column(name = "contract_start")
    private LocalDate start;

    @Column(name = "contract_expectedValue")
    private Integer expectedValue;

    @ElementCollection()
    private List<String> transactions;

    public ContractDatabaseEntity(String id, String name, String accountAcronym, LocalDate start, Integer expectedValue, List<String> transactions) {
        this.id = id;
        this.name = name;
        this.accountAcronym = accountAcronym;
        this.start = start;
        this.expectedValue = expectedValue;
        this.transactions = transactions;
    }

    public ContractDatabaseEntity() {
    }

    public Contract toDomain() {
        return new Contract(id, name,accountAcronym, start, new Value(expectedValue), transactions);
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
}
