package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class RemoveAccount implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String accountAcronym = command.getSecondLevel();
        var cmd = "account delete -a " + accountAcronym;
        if (accountAcronym.isEmpty()){
            cmd = "account";
        }
        return new OverlayCommand(new ExpertCommand(cmd, 2), StateTransition.STAY);
    }
}
