package de.janbraunsdorff.ase.layer.presentation.console.expert.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

public class ExitAction implements Distributor {
    @Override
    public Result distribute(Command command) {
        System.exit(0);
        return null;
    }
}
