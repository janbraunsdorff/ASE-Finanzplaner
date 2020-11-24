package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

import java.util.Map;

public class BankAddAction extends UseCase {

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


        return new BankAddResult(bankDTO);
    }
}
