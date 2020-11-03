package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;

public class BankDefaultAction implements Action {
    @Override
    public Result act(String command) {
        return new BankHelpResult();
    }
}
