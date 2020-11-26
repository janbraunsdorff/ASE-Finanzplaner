package de.janbraunsdorff.ase.layer.presentation.console.expert;

public class DistributorActionFactory {
    private final DistributorAction controller;

    public DistributorActionFactory() {
        this.controller = new DistributorAction();
    }

    public DistributorActionFactory addUseCase(String command, Distributor distributor) {
        this.controller.addUseCase(command, distributor);
        return this;
    }

    public DistributorAction build() {
        return this.controller;
    }
}
