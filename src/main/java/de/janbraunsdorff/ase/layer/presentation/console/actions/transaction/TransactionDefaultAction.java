package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;

public class TransactionDefaultAction implements Action {
    @Override
    public Result act(String command) {
        return new TransactionHelpResult();
    }
}
