package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.contract.ContractIOApplication;
import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class ContractAllAction implements UseCase {

    private final ContractIOApplication service;

    public ContractAllAction(ContractIOApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a")){
            return new ContractHelpResult();
        }

        List<ContractDTO> contractsByAccount = this.service.getContractsByAccount(command.getParameter("-a"));

        return new ContractAllResult(contractsByAccount);
    }
}
