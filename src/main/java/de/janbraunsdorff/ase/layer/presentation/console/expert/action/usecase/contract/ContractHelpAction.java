package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class ContractHelpAction implements UseCase {
    @Override
    public Result act(ExpertCommand command) throws Exception {
        return new ContractHelpResult();
    }
}
