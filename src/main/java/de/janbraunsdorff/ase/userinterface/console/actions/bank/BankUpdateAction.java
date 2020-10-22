package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankUpdateResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.Collections;

public class BankUpdateAction implements Action {

    private final ICrudBank crudBank;

    public BankUpdateAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }


    @Override
    public Result act(String command) {
        String[] s = command.split(" ");
        if (s.length < 4){
            return new BankHelpResult();
        }
        BankEntity updated = crudBank.update(new BankEntity(s[2], s[3], Collections.emptyList(), null));
        return new BankUpdateResult(updated);
    }
}
