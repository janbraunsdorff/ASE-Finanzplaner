package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;

import java.util.List;

public interface ICrudAccount {

    List<AccountMemoryEntity> getAccountsOfBank(String bankId);

    List<AccountMemoryEntity> getAccountsOfBankByAcronym(String acronym);

    AccountMemoryEntity createAccount(String id, AccountMemoryEntity account);

    AccountMemoryEntity createAccountByAcronym(String acronym, AccountMemoryEntity account);

    void deleteById(String s);

    void deleteByAcronym(String s);
}
