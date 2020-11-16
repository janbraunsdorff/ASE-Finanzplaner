package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.List;

public interface ICrudAccount {

    List<AccountEntity> getAccountsOfBank(String bankId);

    List<AccountEntity> getAccountsOfBankByAcronym(String acronym);

    AccountEntity createAccount(String id, AccountEntity account);

    AccountEntity createAccountByAcronym(String acronym, AccountEntity account);

    void deleteById(String s);

    void deleteByAcronym(String s);
}
