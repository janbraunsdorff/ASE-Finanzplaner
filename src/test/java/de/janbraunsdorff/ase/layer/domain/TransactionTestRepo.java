package de.janbraunsdorff.ase.layer.domain;

import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.TransactionNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionTestRepo implements TransactionRepository{
    @Override
    public void createTransaction(Transaction entity) throws AccountNotFoundException {

    }

    @Override
    public int getValueOfAccount(String accountId) {
        if (accountId.length() == 2){
            return 7;
        }

        return 0;
    }

    @Override
    public List<Transaction> getTransactionOfAccount(String id, int count) throws TransactionNotFoundException {
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
}
