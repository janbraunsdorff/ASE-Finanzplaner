package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

import java.util.Map;

public class Cat implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        Map<String, String> tags = this.parseCommand(command, 2);
        String bankAcronym = command.split(" ")[1];

        if (tags.containsKey("-n")){
            String number = tags.get("-n");
            return state.stay("transaction all -a "+ bankAcronym + " -n " + number);

        }
        return state.stay("transaction all -a "+ bankAcronym);
    }
}
