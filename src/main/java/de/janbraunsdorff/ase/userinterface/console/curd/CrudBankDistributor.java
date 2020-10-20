package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.Distributor;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.HashMap;
import java.util.Map;

public class CrudBankDistributor implements Distributor {

    Map<String, Action> actions;

    public CrudBankDistributor (CrudBank crudBank) {
        actions = new HashMap<String, Action>(){{
            put("all", new BankGetAction(crudBank));
            put("add", new BankAddAction(crudBank));
            put("update", new BankUpdateAction(crudBank));
        }};
    }

    @Override
    public Result distribute(String command) {
        String[] s = command.split(" ");
        if (s.length <= 1){
            return new BankHelpResult();
        }
        String action = s[1];
        return  actions.getOrDefault(action, new DefaultAction()).act(command);
    }
}
