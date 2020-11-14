package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.ErrorResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;

import java.util.Map;

public class BankDeleteAction implements Action {

    private final ICrudBank crudBank;

    public BankDeleteAction(ICrudBank crudBank) {
        this.crudBank = crudBank;
    }


    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);

        if (areTagsPresent(tags, "-i")) {
            delete(this.crudBank::deleteById, tags.get("-i"));
        }

        if (areTagsPresent(tags, "-a")) {
            delete(this.crudBank::deleteByAcronym, tags.get("-a"));
        }

        return new BankHelpResult();
    }

    private Result delete(DeleteRepo method, String value) {
        try {
            method.delete(value);
            return new BankDeleteResult(value);
        } catch (IllegalArgumentException ex) {
            return new ErrorResult(ex.getMessage());
        }
    }


    private interface DeleteRepo {
        void delete(String s);
    }

}
