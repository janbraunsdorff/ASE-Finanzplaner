package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
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
    public Result act(String command) throws BankNotFoundExecption, AcronymAlreadyExistsException {
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

    private Result addAccount(Add method, String value, Account entity) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        Account add = method.add(value, entity);
        return new AccountNewResult(add);

    }

    private interface Add {
        Account add(String value, Account entity) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    }
}
