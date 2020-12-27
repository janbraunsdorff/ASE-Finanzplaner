package de.janbraunsdorff.ase.layer.domain.account;

public class AccountGetByAcronymQuery {
    private final String acronym;

    public AccountGetByAcronymQuery(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }
}
