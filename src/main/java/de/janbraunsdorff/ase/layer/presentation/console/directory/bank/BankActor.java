package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;


import de.janbraunsdorff.ase.layer.presentation.console.directory.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

import java.util.HashMap;

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
