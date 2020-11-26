package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Actor;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;

import java.util.HashMap;

public class TransactionActor implements Actor {

    private final HashMap<String, CommandBuilder> builder = new HashMap<>();

    public State act(State state, Command command) {
        if (command.getTopLevel().equals("cd") && command.getSecondLevel().equals("..")){
            return this.builder.get("cd ..").build(state, command);
        }
        String cmd = command.getTopLevel();
        return this.builder.getOrDefault(cmd, (state1, command1) -> state1.stay(new Command("transaction", 0))).build(state, command);


    }

    @Override
    public void addBuilder(String trigger, CommandBuilder builder) {
        this.builder.put(trigger, builder);
    }
}
