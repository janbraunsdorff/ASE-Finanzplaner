package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

public class TransactionHelpAction implements UseCase {
    @Override
    public Result act(Command command) {
        return new TransactionHelpResult();
    }
}
