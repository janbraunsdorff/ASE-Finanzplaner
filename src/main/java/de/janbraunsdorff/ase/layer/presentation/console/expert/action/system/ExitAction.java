package de.janbraunsdorff.ase.layer.presentation.console.expert.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.expert.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

public record ExitAction() implements Distributor {
    @Override
    public Result distribute(ExpertCommand command) {
        System.exit(0);
        return null;
    }
}
