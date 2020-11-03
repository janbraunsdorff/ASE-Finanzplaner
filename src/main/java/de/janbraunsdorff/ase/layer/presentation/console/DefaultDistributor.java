package de.janbraunsdorff.ase.layer.presentation.console;

import de.janbraunsdorff.ase.layer.presentation.console.result.HelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class DefaultDistributor implements Distributor{
    @Override
    public Result distribute(String command) {
        return new HelpResult();
    }
}
