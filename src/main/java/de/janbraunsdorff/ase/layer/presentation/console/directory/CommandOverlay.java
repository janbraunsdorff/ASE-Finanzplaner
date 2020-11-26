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
import java.util.HashMap;
import java.util.Map;

public class CommandOverlay {

    private final DistributorAction controller;
    private State state;
    private final Map<Hierarchy, Actor> actors;

    public CommandOverlay(DistributorAction controller) {
        this.controller = controller;
        this.state = new State(Hierarchy.BANK, null, null, null);

        this.actors = new HashMap<>();
        actors.put(Hierarchy.BANK, new BankActor());
        actors.put(Hierarchy.ACCOUNT, new AccountActor());
        actors.put(Hierarchy.TRANSACTION, new TransactionActor());
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
        this.state = actors.get(this.state.getHierarchy()).act(this.state, shortCommand);
    }


}
