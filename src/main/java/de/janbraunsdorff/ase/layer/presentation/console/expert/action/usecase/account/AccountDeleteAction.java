package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class AccountDeleteAction implements UseCase {
    private final AccountIOApplication service;

    public AccountDeleteAction(AccountIOApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws AccountNotFoundException, TransactionNotFoundException {
        if (!command.areTagsAndValuesPresent("-a")) {
            return new AccountHelpResult();
        }

        AccountDeleteCommand cmd = new AccountDeleteCommand(command.getParameter("-a"));
        this.service.deleteByAcronym(cmd);
        return new AccountDeleteResult(command.getParameter("-a"));
    }
}
