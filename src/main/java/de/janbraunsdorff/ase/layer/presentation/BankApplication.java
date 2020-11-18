package de.janbraunsdorff.ase.layer.presentation;

import de.janbraunsdorff.ase.layer.domain.bank.BankCreateCommand;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.BankNotFoundExecption;

import java.util.List;

public interface BankApplication {
    BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException;
    List<BankDTO> get() throws BankNotFoundExecption;
    void deleteByAcronym(BankDeleteCommand command);
}
