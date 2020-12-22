package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class ListTransaction implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String account = "-a " + state.getAccountIdent();
        String amount = command.getParameter("-n") != null ? "-n " + command.getParameter("-n") : "";
        return new OverlayCommand(new ExpertCommand("transaction all " + account + " " + amount, 2), StateTransition.STAY);
    }
}
