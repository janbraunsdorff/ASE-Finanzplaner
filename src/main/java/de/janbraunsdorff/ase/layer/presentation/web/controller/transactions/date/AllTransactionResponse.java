package de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date;

import java.util.List;

public class AllTransactionResponse {
    private List<TransactionWebDTO> transactions;
    private List<Double> amounts;
    private List<String> labels;
    private String name;
    private String amount;

    public AllTransactionResponse(List<TransactionWebDTO> transactions, String name, String formatted, List<Double> amounts, List<String> labels) {
        this.transactions = transactions;
        this.name = name;
        this.amount = formatted;
        this.amounts = amounts;
        this.labels = labels;
    }

    public AllTransactionResponse() {
    }

    public List<TransactionWebDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionWebDTO> transactions) {
        this.transactions = transactions;
    }

    public List<Double> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
