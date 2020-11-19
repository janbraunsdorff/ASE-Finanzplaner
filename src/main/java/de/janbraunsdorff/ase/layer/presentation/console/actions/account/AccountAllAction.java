package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.persistence.memory.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.AccountApplication;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountResult;

import java.util.List;
import java.util.Map;

public class AccountAllAction implements Action {

    private final AccountApplication service;

    public AccountAllAction(AccountApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws BankNotFoundExecption, AccountNotFoundException {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new AccountHelpResult();
        }

        List<AccountDTO> accountsOfBank = service.getAccountsOfBank(new AccountGetQuery(tags.get("-a")));
        return new AccountResult(accountsOfBank);
    }
}
