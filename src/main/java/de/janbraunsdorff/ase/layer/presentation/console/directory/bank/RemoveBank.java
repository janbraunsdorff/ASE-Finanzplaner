package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class RemoveBank implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String bankAcronym = command.getSecondLevel();
        var cmd = "bank";
        if (!bankAcronym.isEmpty()){
            cmd += " delete -a " + bankAcronym;
        }
        return new OverlayCommand(new ExpertCommand(cmd, 2), StateTransition.STAY);
    }
}
