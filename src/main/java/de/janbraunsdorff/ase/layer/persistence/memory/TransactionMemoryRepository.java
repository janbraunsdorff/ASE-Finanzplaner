package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.repository.TransactionRepository;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionMemoryRepository implements TransactionRepository {
    private final Map<String, Transaction> transactions = new HashMap<>();


    @Override
    public void createTransaction(Transaction entity) {
        this.transactions.put(entity.getId(), entity);
    }

    @Override
    public int getValueOfAccount(String accountId) {
        return this.getTransactionOfAccount(accountId, -1).stream().map(Transaction::getValue).reduce(0, Integer::sum);
    }

    @Override
    public Transaction getTransactionsById(String id){
        return this.transactions.get(id);
    }

    @Override
    public void deleteTransactionById(String id) {
        this.transactions.remove(id);
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        if (count <= 0){
            count = transactions.size();
        }
        return this.transactions.values().stream()
                .filter(a -> a.getAccountId().equals(id))
                .sorted(Comparator.comparing(Transaction::getDate))
                .limit(count)
                .collect(Collectors.toList());
    }
}
