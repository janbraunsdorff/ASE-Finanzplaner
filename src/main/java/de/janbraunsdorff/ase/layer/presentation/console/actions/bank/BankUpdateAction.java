package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankUpdateResult;

import java.util.Map;

public class BankUpdateAction implements Action {

    private final ICrudBank crudBank;

    public BankUpdateAction(ICrudBank crudBank) {
        this.crudBank = crudBank;
    }


    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-i", "-n", "-a")) {
            return new BankHelpResult();
        }

        String id = tags.get("-i");
        String name = tags.get("-n");
        String acronym = tags.get("-a");

        try {
            BankEntity updated = crudBank.update(new BankEntity(name, acronym));
            return new BankUpdateResult(updated);
        } catch (IllegalArgumentException ex) {
            return new ErrorResult(ex.getMessage());
        }
    }
}
