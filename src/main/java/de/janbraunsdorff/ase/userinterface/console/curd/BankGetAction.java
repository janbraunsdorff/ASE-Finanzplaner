package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankAllResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class BankGetAction implements Action{
    private CrudBank crudBank;

    public BankGetAction(CrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        return new BankAllResult(crudBank.get());
    }
}
