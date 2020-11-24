package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

public class TransactionHelpAction extends UseCase {
    @Override
    public Result act(String command) {
        return new TransactionHelpResult();
    }
}
