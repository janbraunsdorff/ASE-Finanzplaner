package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.ErrorResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankDeleteResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;

import java.util.Map;

public class BankDeleteAction implements Action {

    private final ICrudBank crudBank;

    public BankDeleteAction(ICrudBank crudBank){
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
            boolean delete = this.crudBank.delete(id);
            return delete? new BankDeleteResult(id) : new ErrorResult("Bank konnte nicht gelöscht werden");
        }catch (IllegalArgumentException ex){
            return new ErrorResult(ex.getMessage());
        }
    }
}
