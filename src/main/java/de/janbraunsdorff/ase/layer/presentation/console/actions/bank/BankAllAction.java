package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankResult;


public class BankAllAction implements Action {
    private final ICrudBank crudBank;

    public BankAllAction(ICrudBank crudBank) {
        this.crudBank = crudBank;
    }

    @Override
    public Result act(String command) {
        return new BankResult(crudBank.get());
    }
}
