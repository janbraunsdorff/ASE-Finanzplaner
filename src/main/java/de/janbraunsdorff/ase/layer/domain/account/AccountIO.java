package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.domain.*;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountsGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AccountIO implements AccountIOApplication {
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final BankRepository bankRepo;

    public AccountIO(AccountRepository repo, TransactionRepository transactionRepo, BankRepository bankRepo) {
        this.bankRepo = bankRepo;
        this.accountRepo = repo;
        this.transactionRepo = transactionRepo;
    }

    public List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        List<AccountDTO> accounts = new ArrayList<>();
        for (String acronym : query.acronym()) {
            AccountDTO account = this.getAccount(acronym);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public AccountDTO getAccount(AccountGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        return this.getAccount(query.acronym());
    }

    @Override
    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException {
        checkIfBankExists(query.bankAcronym());
        var accounts = this.accountRepo.getAccountsOfBankByBankAcronym(query.bankAcronym());
        var collect = new ArrayList<AccountDTO>();
        for (Account account : accounts) {
            collect.add(this.getAccount(account));
        }
        return collect;
    }

    private Bank checkIfBankExists(String acronym) throws BankNotFoundException {
        return this.bankRepo.getBankByAcronym(acronym);
    }

    @Override
    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws AcronymAlreadyExistsException, BankNotFoundException {
        Bank bank = checkIfBankExists(command.bank());
        Account account = new Account(command.bank(), command.name(), command.number(), command.acronym());
        this.accountRepo.createAccount(account);
        return new AccountDTO(account, 0, 0, bank.getName());
    }

    @Override
    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException {
        for (Transaction t : this.transactionRepo.getTransactionOfAccount(command.accountAcronym(), -1)) {
            this.transactionRepo.deleteTransactionById(t.getId());
        }
        this.accountRepo.deleteAccountByAcronym(command.accountAcronym());
    }

    @NotNull
    private AccountDTO getAccount(String acronym) throws AccountNotFoundException, BankNotFoundException {
        Account a = this.accountRepo.getAccountByAcronym(acronym);
        return this.getAccount(a);
    }

    @NotNull
    private AccountDTO getAccount(Account a) throws BankNotFoundException {
        int numberOfTransactions = Math.toIntExact(transactionRepo.count(a.getAcronym()));
        String bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
        return new AccountDTO(a, numberOfTransactions, transactionRepo.getValueOfAccount(a.getAcronym()), bankName);
    }
}
