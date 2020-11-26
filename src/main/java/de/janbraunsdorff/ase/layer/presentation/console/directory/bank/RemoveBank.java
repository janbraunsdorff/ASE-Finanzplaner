package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;

public class RemoveBank implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String bankAcronym = command.getSecondLevel();
        return state.stay(new Command("bank delete -a " + bankAcronym, 2));
    }
}
