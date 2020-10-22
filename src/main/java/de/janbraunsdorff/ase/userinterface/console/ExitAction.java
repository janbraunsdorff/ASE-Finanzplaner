package de.janbraunsdorff.ase.userinterface.console;

import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class ExitAction implements Distributor{
    @Override
    public Result distribute(String command) {
        System.exit(0);
        return null;
    }
}
