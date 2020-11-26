package de.janbraunsdorff.ase.layer.presentation.console;

import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.system.HelpResult;

public class DistributorDefault implements Distributor {
    @Override
    public Result distribute(Command command) {
        return new HelpResult();
    }
}
