package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankNewResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.Arrays;
import java.util.Collections;

public class BankAddAction implements Action {

    private final ICrudBank crudBank;

    public BankAddAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        String[] s = command.split(" ");
        if (s.length < 6 || !s[2].equals("-n")){
            return new BankHelpResult();
        }

        int acronymIndex = 0;
        for (int i =0; i < s.length; i++){
            if(s[i].equals("-a")){
                acronymIndex = i;
                break;
            }
        }

        if (acronymIndex == 0){
            return new BankHelpResult();
        }


        String name = Arrays
                .stream(Arrays.copyOfRange(s, 3, acronymIndex))
                .reduce((a, b) -> String.join(" ", a ,b))
                .get();

        String acronym = s[acronymIndex +1];

        BankEntity bankEntity = crudBank.create(new BankEntity(null, name, Collections.emptyList(), acronym));
        return new BankNewResult(bankEntity);
    }
}
