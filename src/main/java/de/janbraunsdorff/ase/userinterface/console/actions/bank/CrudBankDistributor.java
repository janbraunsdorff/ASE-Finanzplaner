package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.Distributor;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.actions.DefaultAction;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;

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
        return  this.actions.getOrDefault(action, new DefaultAction()).act(command);
    }

    protected void addAction(String command, Action action){
        this.actions.put(command, action);
    }
}
