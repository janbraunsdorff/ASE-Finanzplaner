package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionResult;

import java.util.List;
import java.util.Map;

public class TransactionAllAction implements Action {

    private final TransactionApplication crudTransaction;

    public TransactionAllAction(TransactionApplication crudTransaction) {
        this.crudTransaction = crudTransaction;
    }

    @Override
    public Result act(String command) throws AccountNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new TransactionHelpResult();
        }

        List<TransactionDTO> allTransactionOfAccount = this.crudTransaction.getTransactions(new TransactionGetQuery(tags.get("-a"), 20));
        return new TransactionResult(allTransactionOfAccount);
    }
}
