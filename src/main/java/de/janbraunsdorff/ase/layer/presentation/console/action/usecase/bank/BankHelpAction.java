package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class BankHelpAction implements UseCase {
    @Override
    public Result act(String command) {
        return new BankHelpResult();
    }
}
