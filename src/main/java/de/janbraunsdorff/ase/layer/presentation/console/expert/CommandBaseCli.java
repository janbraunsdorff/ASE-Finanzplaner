package de.janbraunsdorff.ase.layer.presentation.console.expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.contract.ContractIOApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ExitAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract.ContractAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract.ContractHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract.ContractHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionAddAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionAllAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionDeleteAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionGroupAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionHelpAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionToPdfAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Printer;

public class CommandBaseCli {
    private final DistributorAction controller;
    private final Printer printer;

    public CommandBaseCli(BankApplication bankApplication, AccountIOApplication accountApplication, TransactionApplication transactionApplication, ContractIOApplication contractIOApplication) {
        this.printer = new Printer();
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
                .addCommand("group", new TransactionGroupAction(transactionApplication))
                .addCommand("delete", new TransactionDeleteAction(transactionApplication))
                .addCommand("print", new TransactionToPdfAction(transactionApplication, accountApplication))
                .build();

        DistributorUseCase contractDistributor = new DistributorUsecaseFactory(new ContractHelpResult(), new ContractHelpAction())
                .addCommand("all", new ContractAllAction(contractIOApplication))
                .build();


        this.controller = new DistributorActionFactory()
                .addUseCase("bank", bankDistributor)
                .addUseCase("contract", contractDistributor)
                .addUseCase("account", accountDistributor)
                .addUseCase("transaction", transactionDistributor)
                .addUseCase("exit", new ExitAction())
                .build();
    }

    public DistributorAction getController() {
        return controller;
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name = reader.readLine();
            if (name == null) {
                System.exit(0);
            }
            Result answer = controller.answer(new ExpertCommand(name, 2));
            this.printer.print(answer);
        }
    }
}
