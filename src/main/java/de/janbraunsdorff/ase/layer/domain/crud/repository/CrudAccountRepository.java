package de.janbraunsdorff.ase.layer.domain.crud.repository;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;

import java.util.List;

public interface CrudAccountRepository {
    Account createAccountByBankAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    Account createAccountByBankId(String bank, Account entity) throws BankNotFoundExecption, AcronymAlreadyExistsException;

    List<Account> getAccountsOfBankByBankId(String bank) throws BankNotFoundExecption;
    List<Account> getAccountsOfBankByBankAcronym(String acronym) throws BankNotFoundExecption;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
    void deleteAccountById(String id) throws AccountNotFoundException;
}
