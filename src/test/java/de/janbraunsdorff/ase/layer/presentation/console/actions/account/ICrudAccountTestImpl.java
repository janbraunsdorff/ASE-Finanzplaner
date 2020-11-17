package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;

import java.util.Collections;
import java.util.List;

public class ICrudAccountTestImpl implements ICrudAccount {
    protected String deleteAcronym;
    protected String getAcronym;

    protected String addBankId;
    protected Account addAccount;

    @Override
    public List<Account> getAccountsOfBank(String bankId) throws BankNotFoundExecption {
        return null;
    }

    @Override
    public List<Account> getAccountsOfBankByAcronym(String acronym) throws BankNotFoundExecption {
        this.getAcronym = acronym;
        return Collections.emptyList();
    }

    @Override
    public Account createAccount(String id, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        return null;
    }

    @Override
    public Account createAccountByAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        this.addAccount = account;
        this.addBankId = acronym;
        return account;
    }

    @Override
    public void deleteById(String s) throws AccountNotFoundException {

    }

    @Override
    public void deleteByAcronym(String s) throws AccountNotFoundException {
        this.deleteAcronym = s;
    }
}
