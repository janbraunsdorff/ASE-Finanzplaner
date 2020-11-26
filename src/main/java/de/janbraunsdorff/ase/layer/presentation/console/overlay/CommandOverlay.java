package de.janbraunsdorff.ase.layer.presentation.console.overlay;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.DistributorAction;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.account.AccountActor;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.bank.BankActor;
import de.janbraunsdorff.ase.layer.presentation.console.printing.part.CommandPiece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandOverlay {

    private final DistributorAction controller;
    private State state;
    private final BankActor Bank;
    private final AccountActor bankActor;

    public CommandOverlay(DistributorAction controller) {
        this.controller = controller;
        this.state = new State(Hierarchy.BANK, null, null, null);
        this.Bank = new BankActor();
        this.bankActor = new AccountActor();
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
                this.state = this.Bank.act(this.state, shortCommand);
                break;
            case ACCOUNT:
                this.state = this.bankActor.act(this.state, shortCommand);
                break;
        }
    }


}
