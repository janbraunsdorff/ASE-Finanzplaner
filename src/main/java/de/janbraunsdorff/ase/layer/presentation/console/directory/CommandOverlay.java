package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.directory.account.*;
import de.janbraunsdorff.ase.layer.presentation.console.directory.bank.*;
import de.janbraunsdorff.ase.layer.presentation.console.directory.transaction.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.DistributorAction;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.HelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction.TransactionHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.Printer;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.part.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CommandOverlay {

    private final DistributorAction controller;
    private final Printer printer;
    private State state;
    private final Map<Hierarchy, Actor> actors;

    public CommandOverlay(DistributorAction controller) {
        this.printer = new Printer();
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
                .addBuilder("group", new GroupTransaction())
                .addBuilder("help", new HelpTransaction())
                .addBuilder("rm", new DeleteTransaction())
                .addBuilder("print", new PrintTransaction())
                .build();


        this.actors = new HashMap<Hierarchy, Actor>() {
            {
                put(Hierarchy.BANK, bankActor);
                put(Hierarchy.ACCOUNT, accountActor);
                put(Hierarchy.TRANSACTION, transactionActor);
            }
        };
    }

    public void run() throws IOException {
//      TODO: cleaner reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(this.state.getPath());
            System.out.print(new Command().getPiece());
            System.out.print(" ");
            String shortCommand = reader.readLine();
            if (shortCommand == null) {
                System.exit(0);
            }


            OverlayCommand overlayCommand = actors
                    .get(this.state.getHierarchy())
                    .act(this.state, new ExpertCommand(shortCommand, 1));

            Result answer = controller.answer(overlayCommand.command());

            this.printer.print(answer);

            if (canChangeState(answer)){
                this.state = state.move(overlayCommand);
            }


        }
    }

    private boolean canChangeState(Result r){
        return !(r instanceof ErrorResult)
                && !(r instanceof HelpResult)
                && !(r instanceof AccountHelpResult)
                && !(r instanceof BankHelpResult)
                && !(r instanceof TransactionHelpResult);
    }
}
