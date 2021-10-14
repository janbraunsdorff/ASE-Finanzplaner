package de.janbraunsdorff.ase.layer.presentation.web.controller.contract.data;

import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.TransactionWebDTO;

import java.util.List;

public class ContractWebDTO {
    public final String id;
    public final String name;
    public final String expected;
    public final String expectedAmount;
    public final Boolean isPositive;
    public final Boolean isActive;
    public final String account;
    public final String started;
    public final String ended;
    public final String interval;
    public final String average;
    public final List<TransactionWebDTO> transactions;
    public final String totalExpense;
    public final String thirdParty;
    public final List<String> labels;
    public final List<Double> values;

    public ContractWebDTO(String id, String name, String expected, String expectedAmount, Boolean isPositive, Boolean isActive, String account, String started, String endet, String interval, String avrage, List<TransactionWebDTO> transactions, String totalExpensee, String thirdParty, List<String> labels, List<Double> values) {
        this.id = id;
        this.name = name;
        this.expected = expected;
        this.expectedAmount = expectedAmount;
        this.isPositive = isPositive;
        this.isActive = isActive;
        this.account = account;
        this.started = started;
        this.ended = endet;
        this.interval = interval;
        this.average = avrage;
        this.transactions = transactions;
        this.totalExpense = totalExpensee;
        this.thirdParty = thirdParty;
        this.labels = labels;
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpected() {
        return expected;
    }

    public String getExpectedAmount() {
        return expectedAmount;
    }

    public Boolean getPositive() {
        return isPositive;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getAccount() {
        return account;
    }

    public String getStarted() {
        return started;
    }

    public String getEnded() {
        return ended;
    }

    public String getInterval() {
        return interval;
    }

    public String getAverage() {
        return average;
    }

    public List<TransactionWebDTO> getTransactions() {
        return transactions;
    }

    public String getTotalExpense() {
        return totalExpense;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Double> getValues() {
        return values;
    }
}
