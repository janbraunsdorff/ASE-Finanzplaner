package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
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
    public Result act(String command) throws BankNotFoundExecption {
        Map<String, String> tags = parseCommand(command, 2);
        if (areTagsPresent(tags, "-i")) {
            return getBankEntity(this.crudBank::get, tags.get("-i"));
        }

        if (areTagsPresent(tags, "-a")) {
            return getBankEntity(this.crudBank::getByAcronym, tags.get("-a"));
        }

        return new BankHelpResult();
    }

    private Result getBankEntity(GetBank method, String value) throws BankNotFoundExecption {
        Bank entity = method.get(value);
        return new BankResult(Collections.singletonList(entity));
    }

    private interface GetBank {
        Bank get(String id) throws BankNotFoundExecption;
    }
}
