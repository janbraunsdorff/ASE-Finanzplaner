package de.janbraunsdorff.ase.layer.domain;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TransactionTestRepo implements TransactionRepository {
    public Transaction entity;
    public int count;
    public String accountAcronym;
    @Override
    public void createTransaction(Transaction entity) throws AccountNotFoundException {
        this.entity = entity;
    }

    @Override
    public int getValueOfAccount(String accountId) {
        if (accountId.length() == 2){
            return 7;
        }

        return 0;
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String id, int count) {
        this.count = count;
        this.accountAcronym = id;
        if (id.equals("a1")){
            return new ArrayList<Transaction>(){{
                add(new Transaction("a1", 8, LocalDate.now(), "tp", "cat", false));
                add(new Transaction("a1", 8, LocalDate.now(), "tp", "cat", false));
                add(new Transaction("a1", 8, LocalDate.now(), "tp", "cat", false));
                add(new Transaction("a1", 8, LocalDate.now(), "tp", "cat", false));
            }};
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<Transaction> deleteTransactionById(String id) {
        return Optional.empty();
    }
}
