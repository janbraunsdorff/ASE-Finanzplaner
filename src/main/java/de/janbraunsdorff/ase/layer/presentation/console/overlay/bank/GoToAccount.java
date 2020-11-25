package de.janbraunsdorff.ase.layer.presentation.console.overlay.bank;

import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class GoToAccount implements CommandBuilder {
    @Override
    public State build(State state, String command) {
        String bankAcronym = command.split(" ")[1];
        return state.goDeep(bankAcronym, "account all -a "+ bankAcronym);
    }
}
