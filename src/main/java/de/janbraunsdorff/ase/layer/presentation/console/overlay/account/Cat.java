package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class Cat implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        Command cmd = command.changeStart(2);
        String bankAcronym = command.getSecondLevel();

        if (cmd.areTagsPresent("-n")){
            String number = command.getParameter("-n");
            return state.stay(new Command("transaction all -a "+ bankAcronym + " -n " + number, 2));

        }
        return state.stay(new Command("transaction all -a "+ bankAcronym, 2));
    }
}
