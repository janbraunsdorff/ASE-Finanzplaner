package de.janbraunsdorff.ase.layer.presentation.console.overlay.bank;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class List implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        return state.stay("bank all");
    }
}
