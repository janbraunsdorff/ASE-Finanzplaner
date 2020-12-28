package de.janbraunsdorff.ase.layer.domain.account;

import java.util.List;

public class AccountsGetByAcronymQuery {
    private final List<String> acronym;

    public AccountsGetByAcronymQuery(List<String> acronym) {
        this.acronym = acronym;
    }

    public List<String> getAcronym() {
        return acronym;
    }
}
