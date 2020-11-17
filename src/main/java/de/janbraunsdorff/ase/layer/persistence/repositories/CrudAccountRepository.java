package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.List;

public interface CrudAccountRepository {
    AccountEntity createAccountByBankAcronym(String acronym, AccountEntity account) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    AccountEntity createAccountByBankId(String bank, AccountEntity entity) throws BankNotFoundExecption, AcronymAlreadyExistsException;

    List<AccountEntity> getAccountsOfBankByBankId(String bank) throws BankNotFoundExecption;
    List<AccountEntity> getAccountsOfBankByBankAcronym(String acronym) throws BankNotFoundExecption;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
    void deleteAccountById(String id) throws AccountNotFoundException;
}
