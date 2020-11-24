package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class TransactionHelpAction implements UseCase {
    @Override
    public Result act(String command) {
        return new TransactionHelpResult();
    }
}
