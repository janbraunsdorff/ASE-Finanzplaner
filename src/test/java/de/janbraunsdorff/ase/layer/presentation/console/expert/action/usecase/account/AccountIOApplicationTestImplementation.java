package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountsGetByAcronymQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountIOApplicationTestImplementation implements AccountIOApplication {
    public List<AccountGetByAcronymQuery> accountGetByAcronymQuery;
    public AccountsGetByAcronymQuery accountsGetByAcronymQuery;
    public AccountCreateCommand accountCreateCommand;

    public AccountIOApplicationTestImplementation() {
        this.accountGetByAcronymQuery = new ArrayList<>();
    }

    @Override
    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException, AccountNotFoundException {
        return null;
    }

    @Override
    public List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        this.accountsGetByAcronymQuery = query;
        Account a = new Account("bankAc", "name", "", "ac");
        var aDTO = new AccountDTO(a, 0, 0, "bank");
        return Collections.singletonList(aDTO);
    }

    @Override
    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundException, AcronymAlreadyExistsException {
        this.accountCreateCommand = command;
        Account a = new Account("bankAc", "name", "", "ac");
        return  new AccountDTO(a, 0, 0, "bank");
    }

    @Override
    public AccountDTO getAccount(AccountGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        this.accountGetByAcronymQuery.add(query);
        Account a = new Account("bankAc", "name", "", "ac");
        return new AccountDTO(a, 0, 0, "bank");
    }

    @Override
    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException {

    }
}
