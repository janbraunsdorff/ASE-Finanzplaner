package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;


import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

public class AccountHelpAction implements UseCase {
    @Override
    public Result act(Command command) {
        return new AccountHelpResult();
    }
}
