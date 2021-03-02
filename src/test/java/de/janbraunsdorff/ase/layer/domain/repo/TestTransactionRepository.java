package de.janbraunsdorff.ase.layer.domain.repo;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TestTransactionRepository implements TransactionRepository {

    List<Transaction> transactions;

    public TestTransactionRepository(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void createTransaction(Transaction entity) throws AccountNotFoundException {

    }

    @Override
    public int getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms) {
        return transactions.stream()
                .filter(a -> accountAcronyms.contains(a.getAccountAcronym()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getValueOfAccount(String accountId) {
        return 0;
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        return null;
    }

    @Override
    public Optional<Transaction> deleteTransactionById(String id) throws TransactionNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<Transaction> getTransactionOfAccount(List<String> account, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public Long count(String acronym) {
        return null;
    }

    @Override
    public List<Transaction> getTransactions(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<Transaction> getLastTransactions(int number) {
        return null;
    }

    @Override
    public int getMaxValueOfAccount(String accountAcronym) {
        return 0;
    }
}
