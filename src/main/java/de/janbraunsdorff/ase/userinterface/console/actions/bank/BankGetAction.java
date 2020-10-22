package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.ErrorResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class BankGetAction implements Action {

    private final ICrudBank crudBank;

    public BankGetAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-i")){
            return new BankHelpResult();
        }

        String id = tags.get("-i");
        try {
            BankEntity entity = this.crudBank.get(id);
            return new BankResult(Collections.singletonList(entity));
        }catch (IllegalArgumentException ex){
            return new ErrorResult(ex.getMessage());
        }
    }
}
