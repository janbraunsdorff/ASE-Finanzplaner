package de.janbraunsdorff.ase.layer.presentation.console;


import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class ExitAction implements Distributor {
    @Override
    public Result distribute(String command) {
        System.exit(0);
        return null;
    }
}
