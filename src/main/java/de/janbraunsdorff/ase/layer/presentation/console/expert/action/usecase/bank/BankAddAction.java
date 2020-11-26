package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class BankAddAction implements UseCase {

    private final BankApplication service;

    public BankAddAction(BankApplication service) {
        this.service = service;
    }

    @Override
    public Result act(Command command) throws AcronymAlreadyExistsException {
        if (!command.areTagsAndValuesPresent("-a", "-n")) {
            return new BankHelpResult();
        }

        String name = command.getParameter("-n");
        String acronym = command.getParameter("-a");

        BankDTO bankDTO = this.service.create(new BankCreateCommand(name, acronym));


        return new BankAddResult(bankDTO);
    }
}
