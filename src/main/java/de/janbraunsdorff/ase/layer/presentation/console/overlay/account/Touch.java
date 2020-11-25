package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

import java.util.Map;

public class Touch implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        Map<String, String> tag = parseCommand(command, 1);
        String name = tag.get("-na");
        String number = tag.get("-nr");
        String acronym = tag.get("-ac");

        return state.stay("account add -na " + name + " -nr " + number + " -ac " + acronym + " -a " + state.getBankIdent());
    }
}
