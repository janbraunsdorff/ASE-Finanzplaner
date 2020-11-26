package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.action.UseCase;


public class BankAllAction implements UseCase {
    private final BankApplication service;

    public BankAllAction(BankApplication service) {
        this.service = service;
    }

    @Override
    public Result act(Command command) throws BankNotFoundException {
        return new BankAllResult(this.service.get());
    }
}
