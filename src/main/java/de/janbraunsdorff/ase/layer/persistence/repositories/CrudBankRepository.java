package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface CrudBankRepository {
    BankEntity getBanks(String Id) throws BankNotFoundExecution;
    BankEntity getBankByAcronym(String acronym) throws BankNotFoundExecution;


    List<BankEntity> getBanks();

    void createBank(BankEntity bankEntity) throws Exception;

    BankEntity update(BankEntity bankEntity) throws Exception;

    boolean delete(String bankId) throws Exception;
}
