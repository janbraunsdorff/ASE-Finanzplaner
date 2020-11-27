package de.janbraunsdorff.ase.layer.presentation.console.expert;

import de.janbraunsdorff.ase.layer.domain.account.AccountService;
import de.janbraunsdorff.ase.layer.domain.analyse.TransactionAnalyse;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.analyse.TransactionGroupAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionHelpResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandBaseCli {
    private final DistributorAction controller;

    public CommandBaseCli(BankApplication bankApplication, AccountService accountApplication, TransactionApplication transactionApplication, TransactionAnalyse transactionAnalyse) {
        DistributorUseCase bankDistributor = new DistributorUsecaseFactory(new BankHelpResult(), new BankHelpAction())
                .addCommand("all", new BankAllAction(bankApplication))
                .addCommand("add", new BankAddAction(bankApplication))
                .addCommand("delete", new BankDeleteAction(bankApplication))
                .build();

        DistributorUseCase accountDistributor = new DistributorUsecaseFactory(new AccountHelpResult(), new AccountHelpAction())
                .addCommand("all", new AccountAllAction(accountApplication))
                .addCommand("add", new AccountAddAction(accountApplication))
                .addCommand("delete", new AccountDeleteAction(accountApplication))
                .build();

        DistributorUseCase transactionDistributor = new DistributorUsecaseFactory(new TransactionHelpResult(), new TransactionHelpAction())
                .addCommand("all", new TransactionAllAction(transactionApplication))
                .addCommand("add", new TransactionAddAction(transactionApplication))
                .addCommand("group", new TransactionGroupAction(transactionAnalyse))
                .build();


        this.controller = new DistributorActionFactory()
                .addUseCase("bank", bankDistributor)
                .addUseCase("account", accountDistributor)
                .addUseCase("transaction", transactionDistributor)
                .addUseCase("exit", new ExitAction())
                .build();
    }

    public DistributorAction getController() {
        return controller;
    }

    public void run() throws IOException {
        //      TODO: cleaner reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name = reader.readLine();
            if (name == null) {
                System.exit(0);
            }
            controller.answer(new Command(name, 2));
        }
    }
}
