package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionMemoryRepository implements TransactionRepository {
    private final Map<String, Transaction> transactions = new HashMap<>();


    @Override
    public void createTransaction(Transaction entity) {
        this.transactions.put(entity.getId(), entity);
    }

    @Override
    public int getValueOfAccount(LocalDate start, LocalDate end, Set<String> accountAcronyms) {
        return 0;
    }

    @Override
    public int getValueOfAccount(String accountId) {
        return this.getTransactionOfAccount(accountId, -1)
                .stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
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
}
