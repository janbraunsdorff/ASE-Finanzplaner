package de.janbraunsdorff.ase.layer.presentation.console.expert;


import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

public class DistributorUsecaseFactory {
    private final DistributorUseCase distributor;

    public DistributorUsecaseFactory(Result helpResult, UseCase defaultAction) {
        this.distributor = new DistributorUseCase(helpResult, defaultAction);
    }

    public DistributorUsecaseFactory addCommand(String command, UseCase action) {
        this.distributor.addAction(command, action);
        return this;
    }

    public DistributorUseCase build() {
        return this.distributor;
    }
}
