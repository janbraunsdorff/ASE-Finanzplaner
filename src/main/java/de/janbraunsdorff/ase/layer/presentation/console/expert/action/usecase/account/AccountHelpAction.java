package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;


import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class AccountHelpAction implements UseCase {
    @Override
    public Result act(Command command) {
        return new AccountHelpResult();
    }
}
