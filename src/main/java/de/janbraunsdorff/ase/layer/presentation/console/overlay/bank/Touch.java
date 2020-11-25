package de.janbraunsdorff.ase.layer.presentation.console.overlay.bank;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

import java.util.Map;

public class Touch implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        Map<String, String> tag = parseCommand(command, 1);
        String name = tag.get("-n");
        String acronym = tag.get("-a");
        return state.stay("bank add -n " + name + " -a " + acronym);
    }
}
