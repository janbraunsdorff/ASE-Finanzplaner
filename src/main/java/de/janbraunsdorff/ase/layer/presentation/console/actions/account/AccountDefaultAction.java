package de.janbraunsdorff.ase.layer.presentation.console.actions.account;


import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;

public class AccountDefaultAction implements Action {
    @Override
    public Result act(String command) {
        return new AccountHelpResult();
    }
}
