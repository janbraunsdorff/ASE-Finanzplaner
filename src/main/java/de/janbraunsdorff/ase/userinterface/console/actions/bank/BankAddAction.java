package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankNewResult;

import java.util.Collections;
import java.util.Map;

public class BankAddAction implements Action {

    private final ICrudBank crudBank;

    public BankAddAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-a", "-n")){
            return new BankHelpResult();
        }

        String name = tags.get("-n");
        String acronym = tags.get("-a");
        BankEntity entity = new BankEntity(null, name, Collections.emptyList(), acronym);
        entity = crudBank.create(entity);

        return new BankNewResult(entity);
    }
}
