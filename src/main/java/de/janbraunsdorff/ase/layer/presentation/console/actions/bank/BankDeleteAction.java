package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
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
    public Result act(String command) throws BankNotFoundExecption {
        Map<String, String> tags = parseCommand(command, 2);

        if (areTagsPresent(tags, "-i")) {
            delete(this.crudBank::deleteById, tags.get("-i"));
        }

        if (areTagsPresent(tags, "-a")) {
            delete(this.crudBank::deleteByAcronym, tags.get("-a"));
        }

        return new BankHelpResult();
    }

    private Result delete(DeleteRepo method, String value) throws BankNotFoundExecption {
        method.delete(value);
        return new BankDeleteResult(value);
    }


    private interface DeleteRepo {
        void delete(String s) throws BankNotFoundExecption;
    }

}
