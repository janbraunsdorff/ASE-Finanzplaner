package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.BankApplication;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;

import java.util.Map;

public class BankDeleteAction implements Action {

    private final BankApplication service;

    public BankDeleteAction(BankApplication service) {
        this.service = service;
    }


    @Override
    public Result act(String command) throws BankNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);

        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new BankHelpResult();

        }

        String value = tags.get("-a");

        this.service.deleteByAcronym(new BankDeleteCommand(value));

        return new BankDeleteResult(value);
    }
}
