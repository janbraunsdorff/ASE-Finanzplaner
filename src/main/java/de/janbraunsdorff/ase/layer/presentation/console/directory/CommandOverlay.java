package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.directory.account.*;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.*;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.GoToAccountFromTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.ListTransaction;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.TouchTransaction;
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
        this.state = State.createInitState();

        BankActor bankActor = new ActorFactory<>(new BankActor())
                .addBuilder("ls", new ListBank())
                .addBuilder("cd", new GoToAccountFromBank())
                .addBuilder("cat", new CatAccount())
                .addBuilder("touch", new TouchBank())
                .addBuilder("rm", new RemoveBank())
                .build();

        AccountActor accountActor = new ActorFactory<>(new AccountActor())
                .addBuilder("ls", new ListAccount())
                .addBuilder("cd", new GoToTransactionFromAccount())
                .addBuilder("rm", new RemoveAccount())
                .addBuilder("cat", new CatTransaction())
                .addBuilder("touch", new TouchAccount())
                .addBuilder("cd ..", new GoToBankFromAccount())
                .build();

        TransactionActor transactionActor = new ActorFactory<>(new TransactionActor())
                .addBuilder("cd ..", new GoToAccountFromTransaction())
                .addBuilder("ls", new ListTransaction())
                .addBuilder("touch", new TouchTransaction())
                .build();


        this.actors = new HashMap<>();
        actors.put(Hierarchy.BANK, bankActor);
        actors.put(Hierarchy.ACCOUNT, accountActor);
        actors.put(Hierarchy.TRANSACTION, transactionActor);
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
            this.state = actors
                    .get(this.state.getHierarchy())
                    .act(this.state, new Command(shortCommand, 1));
            controller.answer(this.state.getCommand());
        }
    }
}
