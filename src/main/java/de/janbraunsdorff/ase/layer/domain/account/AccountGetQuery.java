package de.janbraunsdorff.ase.layer.domain.account;

public class AccountGetQuery {
    private final String id;

    public AccountGetQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
