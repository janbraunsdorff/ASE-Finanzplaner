package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

import java.util.HashMap;

public class AccountActor implements Actor {
    private final HashMap<String, CommandBuilder> builder = new HashMap<>();

    public OverlayCommand act(State state, ExpertCommand command) {
        if (command.getTopLevel().equals("cd") && command.getSecondLevel().equals("..")) {
            return this.builder.get("cd ..").build(state, command);
        }
        String cmd = command.getTopLevel();
        return this.builder.getOrDefault(cmd,
                (state1, command1) -> new OverlayCommand(new ExpertCommand("account", 0), StateTransition.STAY))
                .build(state, command);
    }

    @Override
    public void addBuilder(String trigger, CommandBuilder builder) {
        this.builder.put(trigger, builder);
    }
}

