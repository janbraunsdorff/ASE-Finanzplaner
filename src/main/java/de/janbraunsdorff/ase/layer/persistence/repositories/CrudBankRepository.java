package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.List;

public interface CrudBankRepository {
    List<BankMemoryEntity> getBanks() throws BankNotFoundExecption;

    BankMemoryEntity getBankByAcronym(String acronym) throws BankNotFoundExecption;
    BankMemoryEntity getBanks(String Id) throws BankNotFoundExecption;

    void createBank(BankMemoryEntity bankEntity) throws AcronymAlreadyExistsException, IdAlreadyExitsException;

    void deleteBankById(String bankId);
    void deleteBankByAcronym(String bankId);
}
