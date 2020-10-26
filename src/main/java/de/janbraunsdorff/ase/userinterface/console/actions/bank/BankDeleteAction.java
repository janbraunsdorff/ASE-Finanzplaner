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



        if (areTagsPresent(tags, "-i")){
            try {
                String id = tags.get("-i");
                boolean delete = this.crudBank.deleteById(id);
                return delete? new BankDeleteResult(id) : new ErrorResult("Bank konnte nicht gelöscht werden");
            }catch (IllegalArgumentException ex){
                return new ErrorResult(ex.getMessage());
            }

        }

        if (areTagsPresent(tags, "-a")){
            try {
                String id = tags.get("-a");
                boolean delete = this.crudBank.deleteById(id);
                return delete? new BankDeleteResult(id) : new ErrorResult("Bank konnte nicht gelöscht werden");
            }catch (IllegalArgumentException ex){
                return new ErrorResult(ex.getMessage());
            }

        }


        return new BankHelpResult();
    }


}
