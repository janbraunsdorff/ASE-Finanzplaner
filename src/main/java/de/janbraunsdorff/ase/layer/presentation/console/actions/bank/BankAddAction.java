package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankNewResult;

import java.util.Map;

public class BankAddAction implements Action {

    private final BankApplication service;

    public BankAddAction(BankApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws AcronymAlreadyExistsException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a", "-n")) {
            return new BankHelpResult();
        }

        String name = tags.get("-n");
        String acronym = tags.get("-a");

        BankDTO bankDTO = this.service.create(new BankCreateCommand(name, acronym));


        return new BankNewResult(bankDTO);
    }
}
