package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.actions.bank.BankDefaultAction;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;

import java.util.HashMap;
import java.util.Map;

public class CrudTransactionDistributor implements Distributor {

    private final Map<String, Action> actions;

    public CrudTransactionDistributor(){
        this.actions = new HashMap<>();
    }

    @Override
    public Result distribute(String command) {
        String[] s = command.split(" ");
        if (s.length <= 1){
            return new TransactionHelpResult();
        }
        String action = s[1];
        return this.actions.getOrDefault(action, new TransactionDefaultAction()).act(command);
    }

    protected void addAction(String command, Action action){
        this.actions.put(command, action);
    }
}
