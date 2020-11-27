package de.janbraunsdorff.ase.layer.domain.analyse;

public class TransactionGroupCommand {
    private final String account;

    public TransactionGroupCommand(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
