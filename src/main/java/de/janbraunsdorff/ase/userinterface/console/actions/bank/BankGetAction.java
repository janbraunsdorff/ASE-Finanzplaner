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
        if (areTagsPresent(tags, "-i")){
            return getBankEntity(this.crudBank::get, tags.get("-i"));
        }

        if (areTagsPresent(tags, "-a")){
            return getBankEntity(this.crudBank::getByAcronym, tags.get("-a"));
        }

        return new BankHelpResult();
    }

    private Result getBankEntity(GetBank method, String value){
        try {
            BankEntity entity = method.get(value);
            return new BankResult(Collections.singletonList(entity));
        }catch (IllegalArgumentException ex){
            return new ErrorResult(ex.getMessage());
        }
    }

    private interface GetBank {
        BankEntity get(String id);
    }
}
