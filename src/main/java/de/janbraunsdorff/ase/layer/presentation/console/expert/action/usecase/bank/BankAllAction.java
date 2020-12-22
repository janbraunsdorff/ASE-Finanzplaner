package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;


public class BankAllAction implements UseCase {
    private final BankApplication service;

    public BankAllAction(BankApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws BankNotFoundException {
        return new BankAllResult(this.service.get());
    }
}
