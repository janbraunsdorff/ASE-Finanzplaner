package de.janbraunsdorff.ase.layer.presentation.console.overlay.bank;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class Touch implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String name = command.getParameter("-n");
        String acronym = command.getParameter("-a");
        return state.stay(new Command("bank add -n " + name + " -a " + acronym, 2));
    }
}
