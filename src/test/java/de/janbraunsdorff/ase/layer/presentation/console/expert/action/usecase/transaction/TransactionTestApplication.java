package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;

import java.util.Collections;
import java.util.List;

public class TransactionTestApplication implements TransactionApplication {
    protected TransactionGetQuery getQuery;

    @Override
    public TransactionDTO createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException {
        return null;
    }

    @Override
    public List<TransactionDTO> getTransactions(TransactionGetQuery query) {
        this.getQuery = query;
        return Collections.emptyList();
    }

    @Override
    public List<TransactionDTO> deleteTransaction(String... id) {
        return Collections.emptyList();
    }
}
