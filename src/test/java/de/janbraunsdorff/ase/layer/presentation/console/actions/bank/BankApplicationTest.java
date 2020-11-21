package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.BankApplication;

import java.util.Collections;
import java.util.List;

public class BankApplicationTest implements BankApplication {
    public boolean getWasCalled;
    public BankCreateCommand createCommand;
    public BankDeleteCommand deleteCommand;

    @Override
    public BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException {
        this.createCommand = command;
        return null;
    }

    @Override
    public List<BankDTO> get() throws BankNotFoundException {
        this.getWasCalled = true;
        return Collections.emptyList();
    }

    @Override
    public void deleteByAcronym(BankDeleteCommand command) {
        this.deleteCommand = command;
    }
}
