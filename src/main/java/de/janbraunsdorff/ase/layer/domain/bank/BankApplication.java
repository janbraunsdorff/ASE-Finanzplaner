package de.janbraunsdorff.ase.layer.domain.bank;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;

public interface BankApplication {
    BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException;

    List<BankDTO> get() throws BankNotFoundException;

    void deleteByAcronym(BankDeleteCommand command);
}
