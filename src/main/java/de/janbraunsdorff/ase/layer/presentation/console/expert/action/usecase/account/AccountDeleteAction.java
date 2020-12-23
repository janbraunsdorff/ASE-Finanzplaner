package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountService;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class AccountDeleteAction implements UseCase {
    private final AccountService service;

    public AccountDeleteAction(AccountService service) {
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
