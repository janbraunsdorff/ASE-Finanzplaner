package de.janbraunsdorff.ase.layer.domain.bank;


import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;

import java.util.List;

public interface BankRepository {
    List<Bank> getBank() throws BankNotFoundExecption;

    Bank getBankByAcronym(String acronym) throws BankNotFoundExecption;

    void createBank(Bank bankEntity) throws AcronymAlreadyExistsException;

    void deleteBankByAcronym(String bankId);
}
