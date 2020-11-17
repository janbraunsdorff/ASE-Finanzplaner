package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;

import java.util.List;

public interface CrudAccountRepository {
    Account createAccountByBankAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    Account createAccountByBankId(String bank, Account entity) throws BankNotFoundExecption, AcronymAlreadyExistsException;

    List<Account> getAccountsOfBankByBankId(String bank) throws BankNotFoundExecption;
    List<Account> getAccountsOfBankByBankAcronym(String acronym) throws BankNotFoundExecption;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
    void deleteAccountById(String id) throws AccountNotFoundException;
}
