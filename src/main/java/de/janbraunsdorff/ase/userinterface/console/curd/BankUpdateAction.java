package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankUpdateResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.Collections;

public class BankUpdateAction implements Action{

    private CrudBank crudBank;

    public BankUpdateAction(CrudBank crudBank){
        this.crudBank = crudBank;
    }


    @Override
    public Result act(String command) {
        String[] s = command.split(" ");
        if (s.length < 4){
            return new BankHelpResult();
        }
        crudBank.create(new BankEntity(s[2], s[3], Collections.emptyList()));
        return new BankUpdateResult();
    }
}
