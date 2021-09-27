package de.janbraunsdorff.ase.layer.presentation.console.directory.contract;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class GoToBankFromContract implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        return new OverlayCommand(new ExpertCommand("bank all", 0), StateTransition.DEEPER);
    }
}
