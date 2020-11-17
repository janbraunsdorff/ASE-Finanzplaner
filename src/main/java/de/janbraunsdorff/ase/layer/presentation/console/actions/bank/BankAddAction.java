package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankNewResult;

import java.util.Map;

public class BankAddAction implements Action {

    private final ICrudBank crudBank;

    public BankAddAction(ICrudBank crudBank) {
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-a", "-n")) {
            return new BankHelpResult();
        }

        String name = tags.get("-n");
        String acronym = tags.get("-a");
        BankMemoryEntity entity = new BankMemoryEntity(name, acronym);
        try {
            entity = this.crudBank.create(entity);
        } catch (IllegalArgumentException ex) {
            return new ErrorResult(ex.getMessage());
        }

        return new BankNewResult(entity);
    }
}
