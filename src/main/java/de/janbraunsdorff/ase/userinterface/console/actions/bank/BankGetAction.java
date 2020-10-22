package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankAllResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class BankGetAction implements Action {
    private final ICrudBank crudBank;

    public BankGetAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        return new BankAllResult(crudBank.get());
    }
}
