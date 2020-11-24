package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.account.AccountService;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionService;
import de.janbraunsdorff.ase.layer.persistence.memory.AccountMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.BankMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.TransactionMemoryRepository;

public abstract class ApplicationBase {
    private BankRepository bankRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    protected AccountApplication accountApplication;
    protected BankApplication bankApplication;
    protected TransactionApplication transactionApplication;



    protected void intMemoryRepository(){
        this.bankRepository = new BankMemoryRepository();
        this.accountRepository = new AccountMemoryRepository();
        this.transactionRepository = new TransactionMemoryRepository();
    }

    protected void initDefaultApplication(){
        this.accountApplication = new AccountService(this.accountRepository, this.transactionRepository, this.bankRepository);
        this.bankApplication = new BankService(this.bankRepository, this.accountRepository, this.transactionRepository);
        this.transactionApplication = new TransactionService(this.transactionRepository, this.accountRepository);
    }


    protected void addBank(Bank bank) throws AcronymAlreadyExistsException {
        this.bankRepository.createBank(bank);
    }

    protected void addAccount(Account account) throws AcronymAlreadyExistsException {
        this.accountRepository.createAccount(account);
    }

    protected void addTransaction(Transaction transaction) throws AccountNotFoundException {
        this.transactionRepository.createTransaction(transaction);
    }

}
