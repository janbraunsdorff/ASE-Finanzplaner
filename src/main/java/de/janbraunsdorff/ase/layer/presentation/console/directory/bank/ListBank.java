package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public class ListBank implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        return state.stay(new Command("bank all", 2));
    }
}
