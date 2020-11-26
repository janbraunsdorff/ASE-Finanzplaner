package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;
import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

import java.util.List;

public class TransactionAllAction implements UseCase {

    private final TransactionApplication crudTransaction;

    public TransactionAllAction(TransactionApplication crudTransaction) {
        this.crudTransaction = crudTransaction;
    }

    @Override
    public Result act(Command command) throws AccountNotFoundException {
        if (!command.areTagsAndValuesPresent("-a")) {
            return new TransactionHelpResult();
        }

        int number = 20;
        if (command.areTagsAndValuesPresent("-n")) {
            number = Integer.parseInt(command.getParameter("-n"));
        }

        List<TransactionDTO> allTransactionOfAccount = this.crudTransaction.getTransactions(new TransactionGetQuery(command.getParameter("-a"), number));
        return new TransactionAllResult(allTransactionOfAccount);
    }
}
