package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

import java.util.List;
import java.util.Map;

public class TransactionAllAction extends UseCase {

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

        int number = 20;
        if (areTagsAndValuesPresent(tags, "-n")){
            number = Integer.parseInt(tags.get("-n"));
        }

        List<TransactionDTO> allTransactionOfAccount = this.crudTransaction.getTransactions(new TransactionGetQuery(tags.get("-a"), number));
        return new TransactionAllResult(allTransactionOfAccount);
    }
}
