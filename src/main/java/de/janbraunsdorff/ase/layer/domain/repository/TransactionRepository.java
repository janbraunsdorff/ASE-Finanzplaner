package de.janbraunsdorff.ase.layer.domain.repository;

import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction entity) throws AccountNotFoundException;
    int getValueOfAccount(String accountId);
    Transaction getTransactionsById(String id) throws AccountNotFoundException;
    void deleteTransactionById(String id) throws TransactionNotFoundException;
    List<Transaction> getTransactionOfAccount(String id, int count) throws TransactionNotFoundException;

}
