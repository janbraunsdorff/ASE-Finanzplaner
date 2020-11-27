package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public class GroupTransaction implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        return state.stay(new Command("transaction group -a " + state.getAccountIdent(), 2));
    }
}
