package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class DefaultAction implements Action{
    @Override
    public Result act(String command) {
        return new BankHelpResult();
    }
}
