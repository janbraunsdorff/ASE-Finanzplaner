package de.janbraunsdorff.ase.layer.domain.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

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
        return transactions.stream()
                .filter(a -> accountId.contains(a.getAccountAcronym()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
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
        return transactions.stream()
                .filter(a -> acronym.equals(a.getAccountAcronym()))
                .count();
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

    @Override
    public Stream<Transaction> getTransactionByIds(List<String> transactions) {
        return null;
    }
}
