package de.janbraunsdorff.ase.layer.presentation.console.overlay.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class Touch implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String account = " -a " + state.getAccountIdent();
        String value = " -val " + command.getParameter("-val");
        String thp = " -thp " + command.getParameter("-thp");
        String dat = " -dat " + command.getParameter("-dat");
        String cat = " -cat " + command.getParameter("-cat");
        String con = (command.getParameter("-con") != null) ? " -con" : "";

        return state.stay(new Command("transaction add" + account + value + thp + dat + cat + con, 2));
    }
}
