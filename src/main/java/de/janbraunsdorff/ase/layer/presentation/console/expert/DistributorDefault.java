package de.janbraunsdorff.ase.layer.presentation.console.expert;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.HelpResult;

public class DistributorDefault implements Distributor {
    @Override
    public Result distribute(ExpertCommand command) {
        return new HelpResult();
    }
}
