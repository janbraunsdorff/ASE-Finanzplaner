package de.janbraunsdorff.ase.layer.presentation.console.actions.account;


import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;

import java.util.HashMap;
import java.util.Map;

public class CrudAccountDistributor implements Distributor {
    private final Map<String, Action> actions;

    protected CrudAccountDistributor() {
        this.actions = new HashMap<>();
    }

    @Override
    public Result distribute(String command) {
        String[] s = command.split(" ");
        if (s.length <= 1) {
            return new AccountHelpResult();
        }
        String action = s[1];
        return this.actions.getOrDefault(action, new AccountDefaultAction()).act(command);
    }

    protected void addAction(String command, Action action) {
        this.actions.put(command, action);
    }
}
