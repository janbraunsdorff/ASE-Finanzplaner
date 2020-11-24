package de.janbraunsdorff.ase.layer.domain.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;

import java.util.List;

public interface BankApplication {
    BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException;

    List<BankDTO> get() throws BankNotFoundException;

    void deleteByAcronym(BankDeleteCommand command);
}
