package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class RemoveAccount implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String bankAcronym = command.getSecondLevel();
        return new OverlayCommand(new ExpertCommand("account delete -a " + bankAcronym, 2), StateTransition.STAY);
    }
}
