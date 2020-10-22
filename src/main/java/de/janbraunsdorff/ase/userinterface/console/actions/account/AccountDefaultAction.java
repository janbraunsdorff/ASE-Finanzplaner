package de.janbraunsdorff.ase.userinterface.console.actions.account;

import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountHelpResult;

public class AccountDefaultAction implements Action {
    @Override
    public Result act(String command) {
        return new AccountHelpResult();
    }
}
