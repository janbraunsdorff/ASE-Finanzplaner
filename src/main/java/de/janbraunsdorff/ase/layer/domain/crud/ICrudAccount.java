package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;

import java.util.List;

public interface ICrudAccount {

    List<Account> getAccountsOfBank(String bankId);

    List<Account> getAccountsOfBankByAcronym(String acronym);

    Account createAccount(String id, Account account);

    Account createAccountByAcronym(String acronym, Account account);

    void deleteById(String s);

    void deleteByAcronym(String s);
}
