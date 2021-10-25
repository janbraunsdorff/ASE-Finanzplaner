package de.janbraunsdorff.ase.layer.persistence.memory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

public class TransactionMemoryRepository implements TransactionRepository {
    private final Map<String, Transaction> transactions = new HashMap<>();


    @Override
    public void createTransaction(Transaction entity) {
        this.transactions.put(entity.getId(), entity);
    }

    @Override
    public Long getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms) {
        return 0L;
    }

    @Override
    public Long getValueOfAccount(String accountId) {
        return this.getTransactionOfAccount(accountId, -1)
                .stream()
                .map(Transaction::getValue)
                .map(Integer::longValue)
                .reduce(0L, Long::sum);
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String acronym, int count) {
        if (count <= 0) {
            count = transactions.size();
        }
        return this.transactions.values().stream()
                .filter(a -> a.getAccountAcronym().equals(acronym))
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> deleteTransactionById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> getTransactionOfAccount(List<String> account, LocalDate start, LocalDate end) {
        return Collections.emptyList();
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

    @Override
    public Stream<Transaction> getTransactionByIds(List<String> transactions) {
        return null;
    }
}
