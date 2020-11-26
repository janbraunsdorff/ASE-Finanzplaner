package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;

public class RemoveAccount implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String bankAcronym = command.getSecondLevel();
        return state.stay(new Command("account delete -a " + bankAcronym, 2));
    }
}
