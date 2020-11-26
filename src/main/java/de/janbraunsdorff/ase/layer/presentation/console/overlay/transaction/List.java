package de.janbraunsdorff.ase.layer.presentation.console.overlay.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class List implements CommandBuilder {
    @Override
    public State build(State state, Command command) {
        String account = "-a " + state.getAccountIdent();
        String amount = command.getParameter("-n")!= null ? "-n " + command.getParameter("-n") :"";
        return state.stay(new Command("transaction all " + account + " " + amount, 2));
    }
}
