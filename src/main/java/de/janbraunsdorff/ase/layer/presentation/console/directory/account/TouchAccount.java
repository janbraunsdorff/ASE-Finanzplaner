package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class TouchAccount implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String pattern = "account";

        if (command.areTagsAndValuesPresent("-na", "-nr", "-ac")) {
            String name = command.getParameter("-na");
            String number = command.getParameter("-nr");
            String acronym = command.getParameter("-ac");
            pattern = "account add -na " + name + " -nr " + number + " -ac " + acronym + " -a " + state.getBankIdent();
        }


        OverlayCommand cmd = new OverlayCommand(new ExpertCommand(pattern, 2), StateTransition.STAY);
        return cmd;
    }
}
