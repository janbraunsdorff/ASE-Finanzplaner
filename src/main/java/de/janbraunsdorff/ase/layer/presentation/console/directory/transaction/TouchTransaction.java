package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class TouchTransaction implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String account = " -a " + state.getAccountIdent();
        String value = " -val " + command.getParameter("-val");
        String thp = " -thp " + command.getParameter("-thp");
        String dat = " -dat " + command.getParameter("-dat");
        String cat = " -cat " + command.getParameter("-cat");
        String con = (command.getParameter("-con") != null) ? " -con" : "";

        return new OverlayCommand(new ExpertCommand("transaction add" + account + value + thp + dat + cat + con, 2), StateTransition.STAY);
    }
}
