package de.janbraunsdorff.ase.layer.presentation.console.actions;


import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class DistributorBuilder {
    private final GenericDistributor distributor;

    public DistributorBuilder(Result helpResult, Action defaultAction) {
        this.distributor = new GenericDistributor(helpResult, defaultAction);
    }

    public DistributorBuilder addCommand(String command, Action action) {
        this.distributor.addAction(command, action);
        return this;
    }

    public GenericDistributor build() {
        return this.distributor;
    }
}
