package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;


import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class AccountHelpAction implements UseCase {
    @Override
    public Result act(String command) {
        return new AccountHelpResult();
    }
}
