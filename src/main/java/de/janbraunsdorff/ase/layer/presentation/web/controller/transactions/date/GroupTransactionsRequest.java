package de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date;

import java.util.List;

public class GroupTransactionsRequest {
    private List<String> accounts;

    public GroupTransactionsRequest(List<String> accounts) {
        this.accounts = accounts;
    }

    public GroupTransactionsRequest() {
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }
}

