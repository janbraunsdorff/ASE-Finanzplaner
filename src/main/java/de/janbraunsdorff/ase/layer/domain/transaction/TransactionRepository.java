package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction entity) throws AccountNotFoundException;

    int getValueOfAccount(String accountId);

    List<Transaction> getTransactionOfAccount(String id, int count);
}
