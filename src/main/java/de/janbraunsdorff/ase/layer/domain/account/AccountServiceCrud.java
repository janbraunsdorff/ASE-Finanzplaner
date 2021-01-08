package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.domain.*;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceCrud implements AccountApplication {
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final BankRepository bankRepo;

    public AccountServiceCrud(AccountRepository repo, TransactionRepository transactionRepo, BankRepository bankRepo) {
        this.bankRepo = bankRepo;
        this.accountRepo = repo;
        this.transactionRepo = transactionRepo;
    }

    public List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        List<AccountDTO> accounts = new ArrayList<>();
        for (String s : query.acronym()) {
            AccountDTO account = getAccount(s);
            accounts.add(account);
        }
        return accounts;

    }

    @Override
    public AccountDTO getAccount(AccountGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        return getAccount(query.acronym());
    }

    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException {
        checkIfBankExists(query.bankAcronym());
        List<AccountDTO> collect = this.accountRepo.getAccountsOfBankByBankAcronym(query.bankAcronym()).stream().map(a -> {
            int amount = -1;
            String bankName = "nicht bekannt";
            try {
                amount = Math.toIntExact(transactionRepo.count(a.getAcronym()));
                bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
            } catch (BankNotFoundException ignored) {

            }
            return new AccountDTO(a.getName(), a.getNumber(), amount, a.getAcronym(), new Value(transactionRepo.getValueOfAccount(a.getAcronym())), bankName);
        }).collect(Collectors.toList());

        if (collect.isEmpty()) {
            collect.add(new AccountDTO("---", "---", 0, "---", new Value(0), query.bankAcronym()));
        }
        return collect;
    }

    private Bank checkIfBankExists(String acronym) throws BankNotFoundException {
        return this.bankRepo.getBankByAcronym(acronym);
    }

    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws AcronymAlreadyExistsException, BankNotFoundException {
        Bank bank = checkIfBankExists(command.bank());
        Account account = new Account(command.bank(), command.name(), command.number(), command.acronym());
        this.accountRepo.createAccount(account);
        return new AccountDTO(account.getName(), account.getNumber(), 0, account.getAcronym(), new Value(0), bank.getName());
    }

    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException {
        for (Transaction t : this.transactionRepo.getTransactionOfAccount(command.accountAcronym(), -1)) {
            this.transactionRepo.deleteTransactionById(t.getId());
        }
        this.accountRepo.deleteAccountByAcronym(command.accountAcronym());
    }

    @NotNull
    private AccountDTO getAccount(String s) throws AccountNotFoundException, BankNotFoundException {
        Account a = this.accountRepo.getAccountByAcronym(s);
        int amount = Math.toIntExact(transactionRepo.count(a.getAcronym()));
        String bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
        return new AccountDTO(
                a.getName(),
                a.getNumber(),
                amount,
                a.getAcronym(),
                new Value(transactionRepo.getValueOfAccount(a.getAcronym())),
                bankName
        );
    }
}
