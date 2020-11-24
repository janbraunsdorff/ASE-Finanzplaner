package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;


import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

public class AccountHelpAction extends UseCase {
    @Override
    public Result act(String command) {
        return new AccountHelpResult();
    }
}
