package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public class GoToBankFromAccount implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        return state.goUp(new Command("bank all", 2));
    }
}
