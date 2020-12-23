package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;

import java.util.List;

public interface TransactionApplication {
    TransactionDTO createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException;

    List<TransactionDTO> getTransactions(TransactionGetQuery query) throws AccountNotFoundException;

    List<TransactionDTO> deleteTransaction(String... id) throws TransactionNotFoundException;
}
