package de.janbraunsdorff.ase.layer.domain.account;

public class AccountDeleteCommand {
    private final String accountAcronym;

    public AccountDeleteCommand(String bank) {
        this.accountAcronym = bank;
    }

    public String getAccountAcronym() {
        return accountAcronym;
    }
}
