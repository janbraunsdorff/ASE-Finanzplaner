package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    void createTransaction(Transaction entity) throws AccountNotFoundException;

    int getValueOfAccount(String accountId);

    List<Transaction> getTransactionOfAccount(String id, int count);

    Optional<Transaction> deleteTransactionById(String id) throws TransactionNotFoundException;

    List<Transaction> getTransactionOfAccount(List<String> account, LocalDate start, LocalDate end);
}
