package de.janbraunsdorff.ase.layer.presentation.console.expert;

import java.util.HashMap;
import java.util.Map;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

public class DistributorAction {
    private final Map<String, Distributor> useCases;

    protected DistributorAction() {
        this.useCases = new HashMap<>();
    }

    public Result answer(ExpertCommand command) {
        String useCase = command.getTopLevel();
        return this.useCases.getOrDefault(useCase, new DistributorDefault()).distribute(command);
    }

    protected void addUseCase(String command, Distributor distributor) {
        this.useCases.put(command, distributor);
    }
}
