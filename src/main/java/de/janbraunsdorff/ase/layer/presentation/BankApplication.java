package de.janbraunsdorff.ase.layer.presentation;

import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;

import java.util.List;

public interface BankApplication {
    BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException;
    List<BankDTO> get() throws BankNotFoundException;
    void deleteByAcronym(BankDeleteCommand command);
}
