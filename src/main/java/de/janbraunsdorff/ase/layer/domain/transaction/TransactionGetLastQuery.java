package de.janbraunsdorff.ase.layer.domain.transaction;

public class TransactionGetLastQuery {
    private final int number;

    public TransactionGetLastQuery(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
