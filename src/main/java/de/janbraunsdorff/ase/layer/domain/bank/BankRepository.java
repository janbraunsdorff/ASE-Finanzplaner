package de.janbraunsdorff.ase.layer.domain.bank;


import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;

import java.util.List;

public interface BankRepository {
    List<Bank> getBank();

    Bank getBankByAcronym(String acronym) throws BankNotFoundException;

    void createBank(Bank bankEntity) throws AcronymAlreadyExistsException;

    void deleteBankByAcronym(String bankId);
}
