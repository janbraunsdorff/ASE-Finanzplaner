package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;

import java.util.List;

public interface CrudAccountRepository {
    AccountMemoryEntity createAccountByBankAcronym(String acronym, AccountMemoryEntity account) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    AccountMemoryEntity createAccountByBankId(String bank, AccountMemoryEntity entity) throws BankNotFoundExecption, AcronymAlreadyExistsException;

    List<AccountMemoryEntity> getAccountsOfBankByBankId(String bank) throws BankNotFoundExecption;
    List<AccountMemoryEntity> getAccountsOfBankByBankAcronym(String acronym) throws BankNotFoundExecption;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
    void deleteAccountById(String id) throws AccountNotFoundException;
}
