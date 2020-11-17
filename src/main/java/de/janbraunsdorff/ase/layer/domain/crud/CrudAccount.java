package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudAccountRepository;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;

import java.util.List;

public class CrudAccount implements ICrudAccount {
    private final CrudAccountRepository repo;

    public CrudAccount(CrudAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Account> getAccountsOfBank(String bankId) throws BankNotFoundExecption {
        return this.repo.getAccountsOfBankByBankId(bankId);
    }

    @Override
    public List<Account> getAccountsOfBankByAcronym(String acronym) throws BankNotFoundExecption {
        return this.repo.getAccountsOfBankByBankAcronym(acronym);
    }

    @Override
    public Account createAccount(String id, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        return this.repo.createAccountByBankId(id, account);
    }

    @Override
    public Account createAccountByAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        return this.repo.createAccountByBankAcronym(acronym, account);
    }

    @Override
    public void deleteById(String id) throws AccountNotFoundException {
        this.repo.deleteAccountById(id);
    }

    @Override
    public void deleteByAcronym(String acronym) throws AccountNotFoundException {
        this.repo.deleteAccountByAcronym(acronym);
    }
}
