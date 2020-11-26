package de.janbraunsdorff.ase.layer.presentation.console.overlay.bank;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class List implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        return state.stay(new Command("bank all", 2));
    }
}
