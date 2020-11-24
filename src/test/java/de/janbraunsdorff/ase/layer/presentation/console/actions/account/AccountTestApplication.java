package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.*;

import java.util.List;

public class AccountTestApplication implements AccountApplication {
    protected AccountDeleteCommand command;

    @Override
    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException, AccountNotFoundException {
        return null;
    }

    @Override
    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundException, AcronymAlreadyExistsException {
        return null;
    }

    @Override
    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException {
        this.command = command;
    }
}
