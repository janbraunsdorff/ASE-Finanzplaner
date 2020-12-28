package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.*;

import java.util.Collections;
import java.util.List;

public class AccountTestApplication implements AccountApplication {
    protected AccountDeleteCommand deleteCommand;
    protected AccountGetQuery getQuery;
    protected AccountCreateCommand createCommand;

    @Override
    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException, AccountNotFoundException {
        this.getQuery = query;
        return Collections.emptyList();
    }

    @Override
    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundException, AcronymAlreadyExistsException {
        this.createCommand = command;
        return new AccountDTO("", "", 1, "", 1, "");
    }

    @Override
    public AccountDTO getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        return null;
    }

    @Override
    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException {
        this.deleteCommand = command;
    }
}
