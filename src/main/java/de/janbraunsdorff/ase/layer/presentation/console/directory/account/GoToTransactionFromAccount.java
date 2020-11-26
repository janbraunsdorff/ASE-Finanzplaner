package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public class GoToTransactionFromAccount implements CommandBuilder {
    @Override
    public State build(State state, Command command) {

        String bankAcronym = command.getSecondLevel();
        return state.goDeep(bankAcronym, new Command("transaction all -a " + bankAcronym, 2));
    }
}
