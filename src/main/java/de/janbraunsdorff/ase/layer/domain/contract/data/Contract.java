package de.janbraunsdorff.ase.layer.domain.contract.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.janbraunsdorff.ase.layer.domain.Value;

public class Contract {
    private final String id;
    private final String name;
    private final String accountAcronym;
    private final LocalDate start;
    private final LocalDate end;
    private final Value expectedValue;
    private final List<String> transactions;

    public Contract(String id, String name, String accountAcronym, LocalDate start, LocalDate end, Value expectedValue, List<String> transactions) {
        this.id = id;
        this.name = name;
        this.accountAcronym = accountAcronym;
        this.start = start;
        this.end = end;
        this.expectedValue = expectedValue;
        this.transactions = transactions;
    }

    public Contract(String name, String accountAcronym, LocalDate start, LocalDate end, Value expectedValue) {
        this.name = name;
        this.end = end;
        this.id = UUID.randomUUID().toString();
        this.accountAcronym = accountAcronym;
        this.start = start;
        this.expectedValue = expectedValue;
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getAccountAcronym() {
        return accountAcronym;
    }

    public LocalDate getStart() {
        return start;
    }

    public Value getExpectedValue() {
        return expectedValue;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public String getName() {
        return name;
    }

    public LocalDate getEnd() {
        return end;
    }
}
