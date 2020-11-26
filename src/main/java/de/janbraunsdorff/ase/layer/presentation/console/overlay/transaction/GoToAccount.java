package de.janbraunsdorff.ase.layer.presentation.console.overlay.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class GoToAccount implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        return state.goUp(new Command("account all -a " +  state.getBankIdent(), 2));
    }
}
