package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.Distributor;

import java.util.HashMap;
import java.util.Map;

public class CrudBankDistributor implements Distributor {

    Map<String, Action> actions;

    public CrudBankDistributor (CrudBank crudBank) {
        actions = new HashMap<String, Action>(){{
            put("add", new BankAddAction(crudBank));
        }};
    }

    @Override
    public void distribute(String command) {
        String action = command.split(" ")[1];
        actions.getOrDefault(action, new DefaultAction()).act(command);
    }
}
