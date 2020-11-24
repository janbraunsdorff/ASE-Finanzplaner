package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

import java.util.Map;

public class BankDeleteAction implements UseCase {

    private final BankApplication service;

    public BankDeleteAction(BankApplication service) {
        this.service = service;
    }


    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);

        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new BankHelpResult();

        }

        String value = tags.get("-a");

        this.service.deleteByAcronym(new BankDeleteCommand(value));

        return new BankDeleteResult(value);
    }
}
