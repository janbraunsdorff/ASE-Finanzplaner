package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;


import java.util.HashMap;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Actor;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class BankActor implements Actor {

    private final HashMap<String, CommandBuilder> builder = new HashMap<>();

    public OverlayCommand act(State state, ExpertCommand command) {
        String cmd = command.getTopLevel();
        return this.builder.getOrDefault(cmd,
                (state1, command1) -> new OverlayCommand(new ExpertCommand("bank", 0), StateTransition.STAY))
                .build(state, command);
    }

    @Override
    public void addBuilder(String trigger, CommandBuilder builder) {
        this.builder.put(trigger, builder);
    }
}
