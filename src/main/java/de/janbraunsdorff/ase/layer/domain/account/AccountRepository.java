package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.persistence.memory.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    Account getAccountByAcronym(String acronym) throws AccountNotFoundException;
    List<Account> getAccountsOfBankByBankId(String bank) throws BankNotFoundExecption;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
    void deleteAccountById(String id) throws AccountNotFoundException;
}
