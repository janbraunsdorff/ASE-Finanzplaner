package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface CrudBankRepository {
    List<BankEntity> getBanks();

    BankEntity getBankByAcronym(String acronym) throws BankNotFoundExecution;
    BankEntity getBanks(String Id) throws BankNotFoundExecution;

    void createBank(BankEntity bankEntity) throws Exception;
    boolean delete(String bankId) throws Exception;
}
