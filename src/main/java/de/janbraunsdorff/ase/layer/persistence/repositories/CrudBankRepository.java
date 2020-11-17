package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;

import java.util.List;

public interface CrudBankRepository {
    List<Bank> getBanks() throws BankNotFoundExecption;

    Bank getBankByAcronym(String acronym) throws BankNotFoundExecption;
    Bank getBanks(String Id) throws BankNotFoundExecption;

    void createBank(Bank bankEntity) throws AcronymAlreadyExistsException, IdAlreadyExitsException;

    void deleteBankById(String bankId);
    void deleteBankByAcronym(String bankId);
}
