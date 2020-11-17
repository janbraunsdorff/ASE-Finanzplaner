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
        if (!areTagsAndValuesPresent(tags, "-na", "-nr", "-ac", "-a")) {
            return new AccountHelpResult();
        }

        Account account = new Account(tags.get("-na"), tags.get("-nr"), tags.get("-ac"));
        String acronym = tags.get("-a");
        Account add = this.service.createAccountByAcronym(acronym, account);
        return new AccountNewResult(add);



    }

}
