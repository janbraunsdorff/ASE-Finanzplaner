package de.janbraunsdorff.ase.layer.domain.contract.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.janbraunsdorff.ase.layer.domain.Value;

public class Contract {
    private final String id;
    private final String name;
    private final String expected;
    private final String accountAcronym;
    private final LocalDate start;
    private final LocalDate end;
    private final Value expectedValue;
    private final List<String> transactions;
    private final Interval interval;

    public Contract(String id, String name, String expected, String accountAcronym, LocalDate start, LocalDate end, Value expectedValue, List<String> transactions, Interval interval) {
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

    public Contract(String name, String expected, String accountAcronym, LocalDate start, LocalDate end, Value expectedValue, Interval interval) {
        this.name = name;
        this.expected = expected;
        this.end = end;
        this.interval = interval;
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

    public String getExpected() {
        return expected;
    }

    public Interval getInterval() {
        return interval;
    }
}
