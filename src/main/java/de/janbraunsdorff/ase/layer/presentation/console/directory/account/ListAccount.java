package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class ListAccount implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        return new OverlayCommand(new ExpertCommand("account all -a " + state.getBankIdent(), 2), StateTransition.STAY);
    }
}
