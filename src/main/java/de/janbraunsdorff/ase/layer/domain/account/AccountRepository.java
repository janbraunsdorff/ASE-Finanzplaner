package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;

import java.util.List;
import java.util.Set;

public interface AccountRepository {
    void createAccount(Account account) throws AcronymAlreadyExistsException;

    Account getAccountByAcronym(String acronym) throws AccountNotFoundException;

    List<Account> getAccountsOfBankByBankAcronym(String bank) throws BankNotFoundException;
    Set<String> getAccountNamesOfBankByBankAcronym(String bank) throws BankNotFoundException;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
}
