package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

public class BankHelpAction extends UseCase {
    @Override
    public Result act(String command) {
        return new BankHelpResult();
    }
}
