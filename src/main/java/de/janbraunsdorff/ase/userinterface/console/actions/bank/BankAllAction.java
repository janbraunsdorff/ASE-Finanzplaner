package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankResult;


public class BankAllAction implements Action {
    private final ICrudBank crudBank;

    public BankAllAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        return new BankResult(crudBank.get());
    }
}
