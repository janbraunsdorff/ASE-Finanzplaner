package de.janbraunsdorff.ase.layer.domain.crud.repository;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;

import java.util.List;

public interface CrudBankRepository {
    List<Bank> getBanks() throws BankNotFoundExecption;

    Bank getBankByAcronym(String acronym) throws BankNotFoundExecption;
    Bank getBanks(String Id) throws BankNotFoundExecption;

    void createBank(Bank bankEntity) throws AcronymAlreadyExistsException, IdAlreadyExitsException;

    void deleteBankById(String bankId);
    void deleteBankByAcronym(String bankId);
}
