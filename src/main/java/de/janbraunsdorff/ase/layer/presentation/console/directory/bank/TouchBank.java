package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public class TouchBank implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String name = command.getParameter("-n");
        String acronym = command.getParameter("-a");
        return state.stay(new Command("bank add -n " + name + " -a " + acronym, 2));
    }
}
