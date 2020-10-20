package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.CrudBank;

import java.util.Collections;

public class BankAddAction implements Action{

    private CrudBank crudBank;

    public BankAddAction(CrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public void act(String command) {
        String[] s = command.split(" ");
        if (s.length < 3){
            return;
        }
        crudBank.create(new BankEntity(null, s[2], Collections.emptyList()));
    }
}
