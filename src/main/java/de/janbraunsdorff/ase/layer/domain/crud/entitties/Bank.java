package de.janbraunsdorff.ase.layer.domain.crud.entitties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final String name;
    private final List<Account> accounts;
    private final String acronym;

    public Bank(String name, List<Account> accounts, String acronym) {
        this.name = name;
        this.accounts = accounts;
        this.acronym = acronym;
    }

    public Bank(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getAcronym() {
        return this.acronym;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public int getAmountOfAccountsInCent() {
        return this.accounts.stream()
                .mapToInt(Account::getAmountOfAccountInCent)
                .sum();
    }

    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }
}
