package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public class ListTransaction implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String account = "-a " + state.getAccountIdent();
        String amount = command.getParameter("-n") != null ? "-n " + command.getParameter("-n") : "";
        return state.stay(new Command("transaction all " + account + " " + amount, 2));
    }
}
