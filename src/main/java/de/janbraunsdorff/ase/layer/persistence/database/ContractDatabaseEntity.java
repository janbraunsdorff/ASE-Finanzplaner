package de.janbraunsdorff.ase.layer.persistence.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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

    @Column(name = "contract_end")
    private LocalDate end;

    @Column(name = "contract_expectedValue")
    private Integer expectedValue;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> transactions;

    public ContractDatabaseEntity(String id, String name, String accountAcronym, LocalDate start, LocalDate end, Integer expectedValue, List<String> transactions) {
        this.id = id;
        this.name = name;
        this.accountAcronym = accountAcronym;
        this.start = start;
        this.end = end;
        this.expectedValue = expectedValue;
        this.transactions = transactions;
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
    }

    public Contract toDomain() {
        return new Contract(id, name,accountAcronym, start, end, new Value(expectedValue), transactions);
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
