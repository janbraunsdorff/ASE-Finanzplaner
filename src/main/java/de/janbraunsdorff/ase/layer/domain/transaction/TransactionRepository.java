package de.janbraunsdorff.ase.layer.domain.transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;

public interface TransactionRepository {
    void createTransaction(Transaction entity) throws AccountNotFoundException;

    Long getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms);

    Long getValueOfAccount(String accountId);

    List<Transaction> getTransactionOfAccount(String id, int count);

    Optional<Transaction> deleteTransactionById(String id) throws TransactionNotFoundException;

    List<Transaction> getTransactionOfAccount(List<String> account, LocalDate start, LocalDate end);

    Long count(String acronym);

    List<Transaction> getTransactions(LocalDate start, LocalDate end);

    List<Transaction> getLastTransactions(int number);

    int getMaxValueOfAccount(String accountAcronym);

    Stream<Transaction> getTransactionByIds(List<String> transactions);
}
