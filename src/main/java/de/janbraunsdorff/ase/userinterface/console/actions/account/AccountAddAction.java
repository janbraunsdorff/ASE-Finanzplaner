package de.janbraunsdorff.ase.userinterface.console.actions.account;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudAccount;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.ErrorResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountNewResult;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountResult;

import java.util.Collections;
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

        AccountEntity account = new AccountEntity(tags.get("-na"), tags.get("-nr"), tags.get("-ac"), 0, Collections.emptyList());

        if (areTagsPresent(tags, "-a")) {
            return addAccount(this.service::createAccountByAcronym, tags.get("-a"), account);
        }

        if (areTagsPresent(tags, "-i")) {
            return addAccount(this.service::createAccount, tags.get("-i"), account);
        }

        return new AccountHelpResult();
    }

    private Result addAccount(Add method, String value, AccountEntity entity){
        try {
            AccountEntity add = method.add(value, entity);
            return new AccountNewResult(add);
        } catch (IllegalArgumentException ex) {
            return new ErrorResult(ex.getMessage());
        }

    }

    private interface Add {
        AccountEntity add(String value, AccountEntity entity);
    }
}
