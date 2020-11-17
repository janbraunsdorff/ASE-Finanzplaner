package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankResult;

import java.util.Collections;
import java.util.Map;

public class BankGetAction implements Action {

    private final ICrudBank crudBank;

    public BankGetAction(ICrudBank crudBank) {
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (areTagsPresent(tags, "-i")) {
            return getBankEntity(this.crudBank::get, tags.get("-i"));
        }

        if (areTagsPresent(tags, "-a")) {
            return getBankEntity(this.crudBank::getByAcronym, tags.get("-a"));
        }

        return new BankHelpResult();
    }

    private Result getBankEntity(GetBank method, String value) {
        try {
            BankMemoryEntity entity = method.get(value);
            return new BankResult(Collections.singletonList(entity));
        } catch (IllegalArgumentException ex) {
            return new ErrorResult(ex.getMessage());
        }
    }

    private interface GetBank {
        BankMemoryEntity get(String id);
    }
}
