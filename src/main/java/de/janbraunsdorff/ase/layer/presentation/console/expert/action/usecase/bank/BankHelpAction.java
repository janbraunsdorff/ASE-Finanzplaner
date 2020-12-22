package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class BankHelpAction implements UseCase {
    @Override
    public Result act(ExpertCommand command) {
        return new BankHelpResult();
    }
}
