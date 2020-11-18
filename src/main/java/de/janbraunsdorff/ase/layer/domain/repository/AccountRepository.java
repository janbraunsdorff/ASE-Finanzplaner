package de.janbraunsdorff.ase.layer.domain.repository;


import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.BankNotFoundExecption;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    Account getAccountByAcronym(String acronym) throws AccountNotFoundException;
    List<Account> getAccountsOfBankByBankId(String bank) throws BankNotFoundExecption;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
    void deleteAccountById(String id) throws AccountNotFoundException;
}
