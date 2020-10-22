package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankUpdateResult;

import java.util.Collections;
import java.util.Map;

public class BankUpdateAction implements Action {

    private final ICrudBank crudBank;

    public BankUpdateAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }


    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-i", "-n", "-a")){
            return new BankHelpResult();
        }

        String id = tags.get("-i");
        String name = tags.get("-n");
        String acronym = tags.get("-a");

        BankEntity updated = crudBank.update(new BankEntity(id, name, Collections.emptyList(), acronym));
        return new BankUpdateResult(updated);
    }
}
