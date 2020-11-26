package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class GoToBank implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        return state.goUp(new Command("bank all", 2));
    }
}
