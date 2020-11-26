package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.presentation.console.*;
import de.janbraunsdorff.ase.layer.presentation.console.action.system.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account.*;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank.*;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction.TransactionHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandOverlay;

public class ApplicationConsoleBuilder extends ApplicationBase {
    private Distributor transactionDistributor;
    private Distributor accountDistributor;
    private Distributor bankDistributor;
    private DistributorAction controller;

    protected ApplicationConsoleBuilder intMemoryRepo() {
        super.intMemoryRepository();
        return this;
    }

    protected ApplicationConsoleBuilder intJsonRepo(String path) {
        super.intJsonRepository(path);
        return this;
    }

    protected ApplicationConsoleBuilder initDefaultApp() {
        super.initDefaultApplication();
        return this;
    }

    protected ApplicationConsoleBuilder addEntity(Bank bank) throws AcronymAlreadyExistsException {
        super.addBank(bank);
        return this;
    }

    protected ApplicationConsoleBuilder addEntity(Account account) throws AcronymAlreadyExistsException {
        super.addAccount(account);
        return this;
    }

    protected ApplicationConsoleBuilder addEntity(Transaction transaction) throws AccountNotFoundException {
        super.addTransaction(transaction);
        return this;
    }

    protected ApplicationConsoleBuilder createTransactionDistributor(){
        this.transactionDistributor = new DistributorUsecaseFactory(new TransactionHelpResult(), new TransactionHelpAction())
                .addCommand("all", new TransactionAllAction(this.transactionApplication))
                .addCommand("add", new TransactionAddAction(this.transactionApplication))
                .build();
        return this;
    }

    protected ApplicationConsoleBuilder createAccountDistributor(){
        this.accountDistributor = new DistributorUsecaseFactory(new AccountHelpResult(), new AccountHelpAction())
                .addCommand("all", new AccountAllAction(this.accountApplication))
                .addCommand("add", new AccountAddAction(this.accountApplication))
                .addCommand("delete", new AccountDeleteAction(this.accountApplication))
                .build();

        return this;
    }

    protected ApplicationConsoleBuilder createBankDistributor(){
        this.bankDistributor =  new DistributorUsecaseFactory(new BankHelpResult(), new BankHelpAction())
                .addCommand("all", new BankAllAction(this.bankApplication))
                .addCommand("add", new BankAddAction(this.bankApplication))
                .addCommand("delete", new BankDeleteAction(this.bankApplication))
                .build();

        return this;
    }
    
    protected ApplicationConsoleBuilder createActionDistributor(){
        controller = new DistributorActionFactory()
                .addUseCase("bank", this.bankDistributor)
                .addUseCase("account", this.accountDistributor)
                .addUseCase("transaction", this.transactionDistributor)
                .addUseCase("exit", new ExitAction())
                .build();
        return this;
    }
    
    protected CommandBaseCli createCli(){
        System.out.println("Dein Planer steht zur benutzung bereit");
        return new CommandBaseCli(this.controller);
    }

    protected CommandOverlay createOverlay(){
        System.out.println("Dein Planer steht zur benutzung bereit");
        return new CommandOverlay(this.controller);
    }
}
