package de.janbraunsdorff.ase.layer.presentation.console.directory.bank;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class TouchBank implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        var cmd = "bank";
        if (command.areTagsAndValuesPresent("-n", "-a", "-t")){
            String name = command.getParameter("-n");
            String acronym = command.getParameter("-a");
            String type = command.getParameter("-t");

            cmd += " add -n " + name + " -a " + acronym + " -t " + type;
        }

        return new OverlayCommand(new ExpertCommand(cmd , 2), StateTransition.STAY);
    }
}
