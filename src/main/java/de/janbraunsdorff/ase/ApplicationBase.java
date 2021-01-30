package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.account.AccountIO;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.bank.BankService;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionService;
import de.janbraunsdorff.ase.layer.persistence.json.account.AccountJsonRepository;
import de.janbraunsdorff.ase.layer.persistence.json.bank.BankJsonRepository;
import de.janbraunsdorff.ase.layer.persistence.json.transaction.TransactionJsonRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.AccountMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.BankMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.memory.TransactionMemoryRepository;

public abstract class ApplicationBase {
    private BankRepository bankRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    protected AccountIOApplication accountApplication;
    protected BankApplication bankApplication;
    protected TransactionApplication transactionApplication;

    protected void intMemoryRepository() {
        this.bankRepository = new BankMemoryRepository();
        this.accountRepository = new AccountMemoryRepository();
        this.transactionRepository = new TransactionMemoryRepository();
    }

    protected void intJsonRepository(String path) {
        this.bankRepository = new BankJsonRepository(path);
        this.accountRepository = new AccountJsonRepository(path);
        this.transactionRepository = new TransactionJsonRepository(path);
    }

    protected void initDomain() {
        this.accountApplication = new AccountIO(this.accountRepository, this.transactionRepository, this.bankRepository);
        this.bankApplication = new BankService(this.bankRepository, this.accountRepository, this.transactionRepository);
        this.transactionApplication = new TransactionService(this.transactionRepository, this.accountRepository);
    }

    protected void initWithSpring(BankApplication bankApp, AccountIOApplication accountApp, TransactionApplication transactionApp) {
        this.bankApplication = bankApp;
        this.accountApplication = accountApp;
        this.transactionApplication = transactionApp;
    }
}
