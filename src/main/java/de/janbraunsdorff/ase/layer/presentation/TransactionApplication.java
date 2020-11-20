package de.janbraunsdorff.ase.layer.presentation;

import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;

import java.util.List;

public interface TransactionApplication {
    TransactionDTO createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException, AccountNotFoundException;
    List<TransactionDTO> getTransactions(TransactionGetQuery query);
}
