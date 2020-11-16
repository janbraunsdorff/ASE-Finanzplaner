package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface CrudBankRepository {
    List<BankEntity> getBanks();

    BankEntity getBankByAcronym(String acronym) throws BankNotFoundExecption;
    BankEntity getBanks(String Id) throws BankNotFoundExecption;

    void createBank(BankEntity bankEntity) throws Exception;
    void deleteBankById(String bankId);
    void deleteBankByAcronym(String bankId);
}
