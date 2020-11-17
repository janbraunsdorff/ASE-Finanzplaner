package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankNewResult;

import java.util.Map;

public class BankAddAction implements Action {

    private final ICrudBank crudBank;

    public BankAddAction(ICrudBank crudBank) {
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-a", "-n")) {
            return new BankHelpResult();
        }

        String name = tags.get("-n");
        String acronym = tags.get("-a");
        Bank entity = new Bank(name, acronym);
        entity = this.crudBank.create(entity);

        return new BankNewResult(entity);
    }
}
