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
        if (areTagsPresent(tags, "-i")) {
            return deleteAccount(service::deleteById, tags.get("-i"));
        }

        if (areTagsPresent(tags, "-a")) {
            return deleteAccount(service::deleteByAcronym, tags.get("-a"));
        }

        return new AccountHelpResult();

    }

    private Result deleteAccount(Delete method, String value) throws AccountNotFoundException {
        method.delete(value);
        return new AccountDeleteResult(value);
    }

    private interface Delete {
        void delete(String value) throws AccountNotFoundException;
    }
}
