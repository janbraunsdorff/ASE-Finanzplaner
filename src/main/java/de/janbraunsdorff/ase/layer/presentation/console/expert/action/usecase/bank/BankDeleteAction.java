package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class BankDeleteAction implements UseCase {

    private final BankApplication service;

    public BankDeleteAction(BankApplication service) {
        this.service = service;
    }


    @Override
    public Result act(ExpertCommand command) {
        if (!command.areTagsAndValuesPresent("-a")) {
            return new BankHelpResult();

        }

        String value = command.getParameter("-a");

        this.service.deleteByAcronym(new BankDeleteCommand(value));

        return new BankDeleteResult(value);
    }
}
