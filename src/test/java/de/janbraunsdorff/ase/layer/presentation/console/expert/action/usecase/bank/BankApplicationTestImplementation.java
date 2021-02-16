package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;

import java.util.Collections;
import java.util.List;

public class BankApplicationTestImplementation implements BankApplication {
    public String name;
    public String acronym;
    public String type;

    @Override
    public BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException {
        this.name = command.name();
        this.acronym = command.acronym();
        this.type = command.type();

        return null;
    }

    @Override
    public List<BankDTO> get() throws BankNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public void deleteByAcronym(BankDeleteCommand command) {
        this.acronym = command.acronym();
    }
}
