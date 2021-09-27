package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetQuery;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class AccountAllAction implements UseCase {

    private final AccountIOApplication service;

    public AccountAllAction(AccountIOApplication service) {
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
