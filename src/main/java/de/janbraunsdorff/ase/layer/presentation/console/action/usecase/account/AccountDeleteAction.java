package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;

import java.util.Map;

public class AccountDeleteAction extends UseCase {
    private final AccountApplication service;

    public AccountDeleteAction(AccountApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws AccountNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);

        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new AccountHelpResult();
        }

        AccountDeleteCommand cmd = new AccountDeleteCommand(tags.get("-a"));
        this.service.deleteByAcronym(cmd);
        return new AccountDeleteResult(tags.get("-a"));
    }
}
