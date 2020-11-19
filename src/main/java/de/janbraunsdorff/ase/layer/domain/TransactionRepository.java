package de.janbraunsdorff.ase.layer.domain;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.TransactionNotFoundException;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction entity) throws AccountNotFoundException;
    int getValueOfAccount(String accountId);
    List<Transaction> getTransactionOfAccount(String id, int count) throws TransactionNotFoundException;
}
