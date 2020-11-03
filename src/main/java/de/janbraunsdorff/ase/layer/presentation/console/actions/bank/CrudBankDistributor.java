package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.presentation.console.Distributor;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;

import java.util.HashMap;
import java.util.Map;

public class CrudBankDistributor implements Distributor {

    private final Map<String, Action> actions;

    protected CrudBankDistributor () {
        this.actions = new HashMap<>();
    }

    @Override
    public Result distribute(String command) {
        String[] s = command.split(" ");
        if (s.length <= 1){
            return new BankHelpResult();
        }
        String action = s[1];
        return this.actions.getOrDefault(action, new BankDefaultAction()).act(command);
    }

    protected void addAction(String command, Action action){
        this.actions.put(command, action);
    }
}
