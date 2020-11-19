package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.BankApplication;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankResult;


public class BankAllAction implements Action {
    private final BankApplication service;

    public BankAllAction(BankApplication service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws BankNotFoundExecption {
        return new BankResult(this.service.get());
    }
}
