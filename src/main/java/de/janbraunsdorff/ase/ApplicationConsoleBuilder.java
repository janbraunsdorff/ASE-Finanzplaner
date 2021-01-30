package de.janbraunsdorff.ase;

import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandOverlay;
import de.janbraunsdorff.ase.layer.presentation.console.expert.CommandBaseCli;
import de.janbraunsdorff.ase.layer.presentation.console.expert.DistributorAction;

public class ApplicationConsoleBuilder extends ApplicationBase {

    protected ApplicationConsoleBuilder intMemoryRepo() {
        super.intMemoryRepository();
        return this;
    }

    protected ApplicationConsoleBuilder intJsonRepo(String path) {
        super.intJsonRepository(path);
        return this;
    }

    protected ApplicationConsoleBuilder initDefaultApp() {
        super.initDomain();
        return this;
    }

    protected ApplicationConsoleBuilder initWithSpringBeans(BankApplication bankApp, AccountIOApplication accountApp, TransactionApplication transactionApp){
        super.initWithSpring(bankApp, accountApp, transactionApp);
        return this;
    }

    protected CommandBaseCli createCli() {
        System.out.println("Dein Planer steht zur benutzung bereit");
        return new CommandBaseCli(bankApplication, accountApplication, transactionApplication);
    }

    protected CommandOverlay createOverlay() {
        DistributorAction controller = this.createCli().getController();
        System.out.println("Overlay ist aktivirt");
        return new CommandOverlay(controller);
    }
}
