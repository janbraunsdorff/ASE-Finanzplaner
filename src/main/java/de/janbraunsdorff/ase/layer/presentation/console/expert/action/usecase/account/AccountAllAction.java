package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

import java.util.List;

public class AccountAllAction implements UseCase {

    private final AccountApplication service;

    public AccountAllAction(AccountApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws BankNotFoundException, AccountNotFoundException {
        if (!command.areTagsAndValuesPresent("-a")) {
            return new AccountHelpResult();
        }

        List<AccountDTO> accountsOfBank = service.getAccountsOfBank(new AccountGetQuery(command.getParameter("-a")));
        return new AccountAllResult(accountsOfBank);
    }
}
