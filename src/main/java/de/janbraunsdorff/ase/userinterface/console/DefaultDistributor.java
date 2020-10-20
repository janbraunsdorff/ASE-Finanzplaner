package de.janbraunsdorff.ase.userinterface.console;

import de.janbraunsdorff.ase.userinterface.console.result.HelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class DefaultDistributor implements Distributor{
    @Override
    public Result distribute(String command) {
        return new HelpResult();
    }
}
