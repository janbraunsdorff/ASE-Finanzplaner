package de.janbraunsdorff.ase.layer.presentation.console.actions;

import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.actions.transaction.TransactionDefaultAction;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;

import java.util.HashMap;
import java.util.Map;

public class GenericDistributor  implements Distributor {
    private final Map<String, Action> actions;
    private final Result helpResult;
    private final Action defaultAction;

    public GenericDistributor(Result helpResult, Action defaultAction){
        this.helpResult = helpResult;
        this.defaultAction = defaultAction;
        this.actions = new HashMap<>();
    }

    @Override
    public Result distribute(String command) {

        String[] s = command.split(" ");
        if (s.length <= 1) {
            return this.helpResult;
        }
        String action = s[1];
        return this.actions.getOrDefault(action, this.defaultAction).act(command);
    }

    protected void addAction(String command, Action action) {
        this.actions.put(command, action);
    }
}
