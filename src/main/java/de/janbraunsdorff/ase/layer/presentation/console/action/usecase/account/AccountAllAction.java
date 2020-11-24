package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

import java.util.List;
import java.util.Map;

public class AccountAllAction implements UseCase {

    private final AccountApplication service;

    public AccountAllAction(AccountApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws BankNotFoundException, AccountNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new AccountHelpResult();
        }

        List<AccountDTO> accountsOfBank = service.getAccountsOfBank(new AccountGetQuery(tags.get("-a")));
        return new AccountAllResult(accountsOfBank);
    }
}
