package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class CatTransaction implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        ExpertCommand cmd = command.changeStart(2);
        String accountAcronym = command.getSecondLevel();

        var number = "";
        if (cmd.areTagsPresent("-n")) {
            number += " -n " + command.getParameter("-n");
        }

        return new OverlayCommand(new ExpertCommand("transaction all -a " + accountAcronym + number, 2), StateTransition.STAY);
    }
}
