package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.List;

public interface CrudAccountRepository {
    AccountEntity create(String bank, AccountEntity entity) throws Exception;
    List<AccountEntity> getAccountsOfBank(String bank) throws Exception;
    AccountEntity getAccountById(String Id) throws Exception;

    List<AccountEntity> getAccountsByAcronym(String acronym);

    AccountEntity createByAcronym(String acronym, AccountEntity account) throws Exception;
}
