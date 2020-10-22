package de.janbraunsdorff.ase.userinterface.console.actions;

import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;

public class DefaultAction implements Action{
    @Override
    public Result act(String command) {
        return new BankHelpResult();
    }
}
