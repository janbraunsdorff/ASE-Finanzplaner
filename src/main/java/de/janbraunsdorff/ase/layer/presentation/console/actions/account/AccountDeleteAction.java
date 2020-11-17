package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;

import java.util.Map;

public class AccountDeleteAction implements Action {
    private final ICrudAccount service;

    public AccountDeleteAction(ICrudAccount service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws AccountNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);

        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new AccountHelpResult();
        }

        String value = tags.get("-a");
        service.deleteByAcronym(value);
        return new AccountDeleteResult(value);
    }
}
