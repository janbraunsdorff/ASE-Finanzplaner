package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class GoToAccountFromBank implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String bankAcronym = command.getSecondLevel();
        return new OverlayCommand(new ExpertCommand("account all -a " + bankAcronym, 2), StateTransition.DEEPER, bankAcronym);
    }
}
