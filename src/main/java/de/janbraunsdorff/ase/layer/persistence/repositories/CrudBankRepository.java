package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface CrudBankRepository {
    List<BankEntity> getBanks() throws BankNotFoundExecption;

    BankEntity getBankByAcronym(String acronym) throws BankNotFoundExecption;
    BankEntity getBanks(String Id) throws BankNotFoundExecption;

    void createBank(BankEntity bankEntity) throws AcronymAlreadyExistsException, IdAlreadyExitsException;

    void deleteBankById(String bankId);
    void deleteBankByAcronym(String bankId);
}
