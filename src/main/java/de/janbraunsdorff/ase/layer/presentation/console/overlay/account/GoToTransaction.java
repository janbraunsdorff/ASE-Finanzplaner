package de.janbraunsdorff.ase.layer.presentation.console.overlay.account;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.overlay.State;

public class GoToTransaction implements CommandBuilder {
    @Override
    public State build(State state, Command command) {

        String bankAcronym = command.getSecondLevel();
        return state.goDeep(bankAcronym, new Command("transaction all -a "+ bankAcronym, 2));
    }
}
