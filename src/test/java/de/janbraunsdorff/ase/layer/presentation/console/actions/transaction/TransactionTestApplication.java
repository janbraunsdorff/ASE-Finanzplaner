package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;

import java.util.Collection;
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
}
