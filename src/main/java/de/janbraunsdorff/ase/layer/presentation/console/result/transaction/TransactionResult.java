package de.janbraunsdorff.ase.layer.presentation.console.result.transaction;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

import java.util.List;

public class TransactionResult implements Result {
    private final List<Transaction> transactions;

    public TransactionResult(List<Transaction> transactions){

        this.transactions = transactions;
    }

    @Override
    public String print() {
        return null;
    }
}
