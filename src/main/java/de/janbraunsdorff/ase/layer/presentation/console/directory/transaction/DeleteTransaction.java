package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class DeleteTransaction implements CommandBuilder {

    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String ident = command.getSecondLevel();
        return new OverlayCommand(new ExpertCommand("transaction delete -id " + ident, 2), StateTransition.STAY);
    }
}