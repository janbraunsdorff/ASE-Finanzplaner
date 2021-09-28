package de.janbraunsdorff.ase.layer.presentation.console.directory.contract;

import de.janbraunsdorff.ase.layer.presentation.console.directory.CommandBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public class TouchContract  implements CommandBuilder {
    @Override
    public OverlayCommand build(State state, ExpertCommand command) {
        String pattern = "contract";
        if (command.areTagsAndValuesPresent("-n", "-acc", "-start", "-end", "-val", "-exp")){
            var name = " -n " + command.getParameter("-n");
            var acc = " -acc " +command.getParameter("-acc");
            var start = " -start " + command.getParameter("-start");
            var end = " -end " + command.getParameter("-end");
            var val = " -val " + command.getParameter("-val");
            var exp = " -exp " + command.getParameter("-exp");

            pattern += " add" + name + acc + start + end + val + exp;
        }

        return new OverlayCommand(new ExpertCommand(pattern, 2), StateTransition.STAY);
    }
}
