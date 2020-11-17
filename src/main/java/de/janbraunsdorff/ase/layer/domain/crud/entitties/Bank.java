package de.janbraunsdorff.ase.layer.domain.crud.entitties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final String id;
    private final String name;
    private final List<Account> accounts;
    private final String acronym;

    public Bank(String id, String name, List<Account> accounts, String acronym) {
        this.id = id;
        this.name = name;
        this.accounts = accounts;
        this.acronym = acronym;
    }

    public Bank(String name, String acronym) {
        this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return this.id;
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
