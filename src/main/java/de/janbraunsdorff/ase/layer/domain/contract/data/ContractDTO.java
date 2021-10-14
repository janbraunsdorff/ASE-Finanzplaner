package de.janbraunsdorff.ase.layer.domain.contract.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.TransactionWebDTO;

public final class ContractDTO {
    private final String id;
    private final String name;
    private final String expected;
    private final LocalDate start;
    private final LocalDate end;
    private final int numberOfTransactions;
    private final Value averageAmount;
    private Value expectedAmount;
    private final Value totalExpense;
    private final Interval interval;
    private final String account;
    private final String thirdParty;
    private final List<TransactionWebDTO> transactions;
    private final List<Double> values;

    public ContractDTO(
            String id,
            String name,
            String expected,
            LocalDate start,
            LocalDate end,
            int numberOfTransactions,
            Value averageAmount,
            Value expectedAmount,
            Value totalExpense,
            Interval interval,
            String account,
            String thirdParty, List<TransactionWebDTO> transactions, List<Double> values) {
        this.id = id;
        this.name = name;
        this.expected = expected;
        this.start = start;
        this.end = end;
        this.numberOfTransactions = numberOfTransactions;
        this.averageAmount = averageAmount;
        this.expectedAmount = expectedAmount;
        this.totalExpense = totalExpense;
        this.interval = interval;
        this.account = account;
        this.thirdParty = thirdParty;
        this.transactions = transactions;
        this.values = values;
    }

    public static ContractDTO nomalize(ContractDTO contractDTO) {
        contractDTO.expectedAmount = new Value((int)(contractDTO.expectedAmount.getValue().doubleValue() / contractDTO.interval.getFactor()));
        return contractDTO;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String expected() {
        return expected;
    }

    public LocalDate start() {
        return start;
    }

    public LocalDate end() {
        return end;
    }

    public int numberOfTransactions() {
        return numberOfTransactions;
    }

    public Value averageAmount() {
        return averageAmount;
    }

    public Value expectedAmount() {
        return expectedAmount;
    }

    public Value totalExpense() {
        return totalExpense;
    }

    public Interval interval() {
        return interval;
    }

    public String account() {
        return account;
    }

    public String thirdParty() {
        return thirdParty;
    }

    public List<TransactionWebDTO> transactions(){
        return transactions;
    }

    public List<Double> getValues() {
        return values;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ContractDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.expected, that.expected) &&
                Objects.equals(this.start, that.start) &&
                Objects.equals(this.end, that.end) &&
                this.numberOfTransactions == that.numberOfTransactions &&
                Objects.equals(this.averageAmount, that.averageAmount) &&
                Objects.equals(this.expectedAmount, that.expectedAmount) &&
                Objects.equals(this.totalExpense, that.totalExpense) &&
                Objects.equals(this.interval, that.interval) &&
                Objects.equals(this.account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expected, start, end, numberOfTransactions, averageAmount, expectedAmount, totalExpense, interval, account);
    }

    @Override
    public String toString() {
        return "ContractDTO[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "expected=" + expected + ", " +
                "start=" + start + ", " +
                "end=" + end + ", " +
                "numberOfTransactions=" + numberOfTransactions + ", " +
                "averageAmount=" + averageAmount + ", " +
                "expectedAmount=" + expectedAmount + ", " +
                "totalExpense=" + totalExpense + ", " +
                "interval=" + interval + ", " +
                "account=" + account + ']';
    }

}
