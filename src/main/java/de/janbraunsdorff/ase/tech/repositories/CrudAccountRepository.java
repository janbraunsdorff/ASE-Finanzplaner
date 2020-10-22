package de.janbraunsdorff.ase.tech.repositories;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;

import java.util.List;

public interface CrudAccountRepository {
    AccountEntity create(String bank, AccountEntity entity) throws Exception;
    List<AccountEntity> getAccountsOfBank(String bank) throws Exception;
    AccountEntity getAccountById(String Id) throws Exception;
}
