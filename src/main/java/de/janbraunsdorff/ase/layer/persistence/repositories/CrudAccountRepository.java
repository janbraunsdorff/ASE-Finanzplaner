package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.List;

public interface CrudAccountRepository {
    AccountEntity createAccountByBankId(String bank, AccountEntity entity) throws Exception;

    List<AccountEntity> getAccountsOfBankByBankId(String bank) throws Exception;


    List<AccountEntity> getAccountsOfBankByBankAcronym(String acronym) throws Exception;

    AccountEntity createByAcronym(String acronym, AccountEntity account) throws Exception;

    void deleteAccountByAcronym(String acronym) throws Exception;

    void deleteAccountById(String id) throws Exception;
}
