package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;

public class Touch implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String pattern = "account";

        if (command.areTagsAndValuesPresent("-na", "-nr", "-ac")){
            String name = command.getParameter("-na");
            String number = command.getParameter("-nr");
            String acronym = command.getParameter("-ac");
            pattern = "account add -na " + name + " -nr " + number + " -ac " + acronym + " -a " + state.getBankIdent();
        }


        Command cmd = new Command(pattern, 2);
        return state.stay(cmd);
    }
}
