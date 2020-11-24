package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

import java.util.Map;

public class AccountAddAction extends UseCase {

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

        AccountCreateCommand cmd = new AccountCreateCommand(tags.get("-a"), tags.get("-na"), tags.get("-nr"), tags.get("-ac"));
        AccountDTO account = service.createAccountByAcronym(cmd);
        return new AccountAddResult(account);


    }

}
