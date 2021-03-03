package de.janbraunsdorff.ase.layer.domain.repo;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAccountRepository implements AccountRepository {

    private List<Account> accounts;
    private Account account;

    public Account createAccount;
    public String getAccountByAcronym;
    public String getAccountsOfBankByBankAcronym;
    public String getAccountNamesOfBankByBankAcronym;
    public String deleteAccountByAcronym;
    public Boolean getAllWasCalled;

    public TestAccountRepository(List<Account> accounts) {
        this.accounts = accounts;
    }

    public TestAccountRepository(Account account) {
        this.account = account;
    }

    @Override
    public void createAccount(Account account) throws AcronymAlreadyExistsException {
        this.createAccount = account;
    }

    @Override
    public Account getAccountByAcronym(String acronym) throws AccountNotFoundException {
        this.getAccountByAcronym = acronym;
        return this.account;
    }

    @Override
    public List<Account> getAccountsOfBankByBankAcronym(String bank) throws BankNotFoundException {
        this.getAccountsOfBankByBankAcronym = bank;
        return this.accounts;
    }

    @Override
    public Set<String> getAccountNamesOfBankByBankAcronym(String bank) throws BankNotFoundException {
        this.getAccountNamesOfBankByBankAcronym = bank;
        return this.accounts.stream()
                .filter(e -> e.getBankAcronym().equals(bank))
                .map(Account::getAcronym).collect(Collectors.toSet());
    }

    @Override
    public void deleteAccountByAcronym(String acronym) throws AccountNotFoundException {
        this.deleteAccountByAcronym = acronym;
    }

    @Override
    public List<Account> getAll() {
        this.getAllWasCalled = true;
        return accounts;
    }
}