package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class BankAddAction implements UseCase {

    private final BankApplication service;

    public BankAddAction(BankApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws AcronymAlreadyExistsException {
        if (!command.areTagsAndValuesPresent("-a", "-n", "-t")) {
            return new BankHelpResult();
        }

        String name = command.getParameter("-n");
        String acronym = command.getParameter("-a");
        String type = command.getParameter("-t");

        BankDTO bankDTO = this.service.create(new BankCreateCommand(name, acronym, type));


        return new BankAddResult(bankDTO);
    }
}
