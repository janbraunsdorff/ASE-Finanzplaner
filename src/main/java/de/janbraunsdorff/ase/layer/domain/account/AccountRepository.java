package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;

import java.util.List;

public interface AccountRepository {
    void createAccount(Account account) throws AcronymAlreadyExistsException;

    Account getAccountByAcronym(String acronym) throws AccountNotFoundException;

    List<Account> getAccountsOfBankByBankAcronym(String bank) throws BankNotFoundException;

    void deleteAccountByAcronym(String acronym) throws AccountNotFoundException;
}
