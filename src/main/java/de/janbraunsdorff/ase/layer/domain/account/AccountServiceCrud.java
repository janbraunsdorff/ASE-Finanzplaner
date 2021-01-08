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
    private final AccountRepository repo;
    private final TransactionRepository transactionRepo;
    private final BankRepository bankRepo;

    public AccountServiceCrud(AccountRepository repo, TransactionRepository transactionRepo, BankRepository bankRepo) {
        this.repo = repo;
        this.transactionRepo = transactionRepo;
        this.bankRepo = bankRepo;
    }

    public List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        List<AccountDTO> accounts = new ArrayList<>();
        for (String s : query.acronym()){
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
        this.bankRepo.getBankByAcronym(query.id());
        List<Account> accounts = this.repo.getAccountsOfBankByBankAcronym(query.id());
        List<AccountDTO> collect = accounts.stream().map(a -> {
            int amount = -1;
            String bankName = "nicht bekannt";
            try {
                amount = transactionRepo.getTransactionOfAccount(a.getAcronym(), -1).size();
                bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
            } catch (BankNotFoundException ignored) {

            }
            return new AccountDTO(a.getName(), a.getNumber(), amount, a.getAcronym(), new Value(transactionRepo.getValueOfAccount(a.getAcronym())), bankName);
        }).collect(Collectors.toList());

        if (collect.isEmpty()) {
            collect.add(new AccountDTO("---", "---", 0, "---", new Value(0), query.id()));
        }
        return collect;
    }

    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws AcronymAlreadyExistsException, BankNotFoundException {
        Bank bank = this.bankRepo.getBankByAcronym(command.bank());
        Account account = new Account(command.bank(), command.name(), command.number(), command.acronym());
        this.repo.createAccount(account);
        return new AccountDTO(account.getName(), account.getNumber(), 0, account.getAcronym(), new Value(0), bank.getName());
    }

    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException {
        for (Transaction t : this.transactionRepo.getTransactionOfAccount(command.accountAcronym(), -1)) {
            this.transactionRepo.deleteTransactionById(t.getId());
        }
        this.repo.deleteAccountByAcronym(command.accountAcronym());
    }

    @NotNull
    private AccountDTO getAccount(String s) throws AccountNotFoundException, BankNotFoundException {
        Account a = this.repo.getAccountByAcronym(s);
        int amount = transactionRepo.getTransactionOfAccount(a.getAcronym(), -1).size();
        String bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
        return new AccountDTO(
                a.getName(),
                a.getNumber(),
                amount,
                a.getAcronym(),
                new Value(transactionRepo.getValueOfAccount(a.getAcronym())), bankName
        );
    }
}
