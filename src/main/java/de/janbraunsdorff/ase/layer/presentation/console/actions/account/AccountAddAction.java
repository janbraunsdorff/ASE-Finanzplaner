package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountNewResult;

import java.util.Map;

public class AccountAddAction implements Action {

    private final ICrudAccount service;

    public AccountAddAction(ICrudAccount service) {
        this.service = service;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-na", "-nr", "-ac")) {
            return new AccountHelpResult();
        }

        Account account = new Account(tags.get("-na"), tags.get("-nr"), tags.get("-ac"));

        if (areTagsPresent(tags, "-a")) {
            return addAccount(this.service::createAccountByAcronym, tags.get("-a"), account);
        }

        if (areTagsPresent(tags, "-i")) {
            return addAccount(this.service::createAccount, tags.get("-i"), account);
        }

        return new AccountHelpResult();
    }

    private Result addAccount(Add method, String value, Account entity) {
        try {
            Account add = method.add(value, entity);
            return new AccountNewResult(add);
        } catch (IllegalArgumentException ex) {
            return new ErrorResult(ex.getMessage());
        }

    }

    private interface Add {
        Account add(String value, Account entity);
    }
}
