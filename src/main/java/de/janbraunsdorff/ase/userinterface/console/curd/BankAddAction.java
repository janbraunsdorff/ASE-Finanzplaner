package de.janbraunsdorff.ase.userinterface.console.curd;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankNewResult;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

import java.util.Arrays;
import java.util.Collections;

public class BankAddAction implements Action{

    private final ICrudBank crudBank;

    public BankAddAction(ICrudBank crudBank){
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        String[] s = command.split(" ");
        if (s.length < 4 || !s[2].equals("-n")){
            return new BankHelpResult();
        }
        String name = Arrays.stream(Arrays.copyOfRange(s, 3, s.length)).reduce((a, b) -> String.join(" ", a ,b)).get();
        BankEntity bankEntity = crudBank.create(new BankEntity(null, name, Collections.emptyList(), null));
        return new BankNewResult(bankEntity);
    }
}
