package de.janbraunsdorff.ase.layer.domain.transaction;

public class TransactionGetQuery {
    private final String account;
    private final Integer count;

    public TransactionGetQuery(String account, Integer count) {
        this.account = account;
        this.count = count;
    }

    public String getAccount() {
        return account;
    }

    public Integer getCount() {
        return count;
    }
}
