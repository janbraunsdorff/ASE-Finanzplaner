package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountNewResult;

import java.util.Map;

public class AccountAddAction implements Action {

    private final AccountApplication service;

    public AccountAddAction(AccountApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws BankNotFoundException, AcronymAlreadyExistsException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-na", "-nr", "-ac", "-a")) {
            return new AccountHelpResult();
        }

        AccountCreateCommand cmd = new AccountCreateCommand(tags.get("-a"), tags.get("-na"), tags.get("-ar"), tags.get("-ac"));
        AccountDTO account = service.createAccountByAcronym(cmd);
        return new AccountNewResult(account);


    }

}
