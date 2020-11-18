package de.janbraunsdorff.ase.layer.presentation;

import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;

import java.util.List;

public interface TransactionApplication {
    void createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException;
    List<TransactionDTO> getTransactions(TransactionGetQuery query) throws TransactionNotFoundException;
}
