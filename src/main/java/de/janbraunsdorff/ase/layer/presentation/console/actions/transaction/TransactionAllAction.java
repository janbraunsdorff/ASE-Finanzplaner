package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudTransaction;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionResult;

import java.util.List;
import java.util.Map;

public class TransactionAllAction implements Action {

    private final ICrudTransaction crudTransaction;

    public TransactionAllAction(ICrudTransaction crudTransaction) {
        this.crudTransaction = crudTransaction;
    }

    @Override
    public Result act(String command) throws AccountNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a")){
            return new TransactionHelpResult();
        }

        List<Transaction> allTransactionOfAccount = this.crudTransaction.getAllTransactionOfAccount(tags.get("-a"));
        return new TransactionResult(allTransactionOfAccount);
    }
}
