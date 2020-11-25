package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class GoToTransaction implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        String bankAcronym = command.split(" ")[1];
        return state.goDeep(bankAcronym, "transaction all -a "+ bankAcronym);
    }
}
