package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class GoToBank implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        return state.goUp("bank all");
    }
}
