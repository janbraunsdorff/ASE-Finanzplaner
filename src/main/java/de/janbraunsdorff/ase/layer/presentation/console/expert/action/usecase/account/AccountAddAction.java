package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class AccountAddAction implements UseCase {

    private final AccountIOApplication service;

    public AccountAddAction(AccountIOApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws BankNotFoundException, AcronymAlreadyExistsException {
        if (!command.areTagsAndValuesPresent("-na", "-nr", "-ac", "-a")) {
            return new AccountHelpResult();
        }

        AccountCreateCommand cmd = new AccountCreateCommand(command.getParameter("-a"), command.getParameter("-na"), command.getParameter("-nr"), command.getParameter("-ac"));
        AccountDTO account = service.createAccountByAcronym(cmd);
        return new AccountAddResult(account);


    }

}
