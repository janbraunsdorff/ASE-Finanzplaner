package de.janbraunsdorff.ase.layer.presentation.web.controller.account;

import java.util.List;

public class ResponseBankOverview {
    private final String name;
    private final String acronym;
    private final List<ResponseAccountOverview> accounts;

    public ResponseBankOverview(String name, String acronym, List<ResponseAccountOverview> accounts) {
        this.name = name;
        this.acronym = acronym;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public List<ResponseAccountOverview> getAccounts() {
        return accounts;
    }
}
