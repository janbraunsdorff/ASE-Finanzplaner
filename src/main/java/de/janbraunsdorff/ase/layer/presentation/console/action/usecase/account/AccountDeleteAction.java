package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

public class AccountDeleteAction implements UseCase {
    private final AccountApplication service;

    public AccountDeleteAction(AccountApplication service) {
        this.service = service;
    }

    @Override
    public Result act(Command command) throws AccountNotFoundException {
        if (!command.areTagsAndValuesPresent("-a")) {
            return new AccountHelpResult();
        }

        AccountDeleteCommand cmd = new AccountDeleteCommand(command.getParameter("-a"));
        this.service.deleteByAcronym(cmd);
        return new AccountDeleteResult(command.getParameter("-a"));
    }
}
