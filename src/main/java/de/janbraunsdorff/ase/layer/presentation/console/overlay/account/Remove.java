package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class Remove implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String bankAcronym = command.getSecondLevel();
        return state.stay(new Command("account delete -a " + bankAcronym, 2));
    }
}
