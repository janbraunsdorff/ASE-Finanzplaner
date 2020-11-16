package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.List;

public interface CrudAccountRepository {
    AccountEntity createAccountByBankId(String bank, AccountEntity entity) throws Exception;

    List<AccountEntity> getAccountsOfBankById(String bank) throws Exception;


    List<AccountEntity> getAccountsOfBankByAcronym(String acronym) throws Exception;

    AccountEntity createByAcronym(String acronym, AccountEntity account) throws Exception;

    boolean deleteAccountByAcronym(String acronym);

    boolean deleteAccountById(String id);
}
