package de.janbraunsdorff.ase.layer.presentation.console.expert;

import java.util.HashMap;
import java.util.Map;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ErrorResult;

public class DistributorUseCase implements Distributor {
    private final Map<String, UseCase> actions;
    private final Result helpResult;
    private final UseCase defaultAction;

    public DistributorUseCase(Result helpResult, UseCase defaultAction) {
        this.helpResult = helpResult;
        this.defaultAction = defaultAction;
        this.actions = new HashMap<>();
    }

    @Override
    public Result distribute(ExpertCommand command) {

        if (command.getTopLevel().isEmpty()) {
            return this.helpResult;
        }
        String action = command.getSecondLevel();
        try {
            return this.actions.getOrDefault(action, this.defaultAction).act(command);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    public void addAction(String command, UseCase action) {
        this.actions.put(command, action);
    }
}
