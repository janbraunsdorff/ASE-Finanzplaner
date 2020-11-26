package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.directory.account.AccountActor;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.BankActor;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.TransactionActor;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.DistributorAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part.CommandPiece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandOverlay {

    private final DistributorAction controller;
    private State state;
    private final BankActor bankActor;
    private final AccountActor accountActor;
    private final TransactionActor transactionActor;

    public CommandOverlay(DistributorAction controller) {
        this.controller = controller;
        this.state = new State(Hierarchy.BANK, null, null, null);
        this.bankActor = new BankActor();
        this.accountActor = new AccountActor();
        transactionActor = new TransactionActor();
    }

    public void run() throws IOException {
//      TODO: cleaner reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(this.state.getPath());
            System.out.print(new CommandPiece().getPiece());
            System.out.print(" ");
            String shortCommand = reader.readLine();
            if (shortCommand == null) {
                System.exit(0);
            }
            createCommand(new Command(shortCommand, 1));
            controller.answer(this.state.getCommand());
        }
    }

    public void createCommand(Command shortCommand) {
        switch (this.state.getHierarchy()) {
            case BANK:
                this.state = this.bankActor.act(this.state, shortCommand);
                break;
            case ACCOUNT:
                this.state = this.accountActor.act(this.state, shortCommand);
                break;
            case Transaction:
                this.state = this.transactionActor.act(this.state, shortCommand);
                break;
        }
    }


}
