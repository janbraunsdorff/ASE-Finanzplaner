package de.janbraunsdorff.ase.layer.presentation.console.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class ExitAction implements Distributor {
    @Override
    public Result distribute(String command) {
        System.exit(0);
        return null;
    }
}
