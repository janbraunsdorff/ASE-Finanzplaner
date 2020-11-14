package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface CrudBankRepository {
    BankEntity getBankById(String Id) throws Exception;

    List<BankEntity> getBankById() throws Exception;

    BankEntity getBankByAcronym(String acronym);

    BankEntity create(BankEntity bankEntity) throws Exception;

    BankEntity update(BankEntity bankEntity) throws Exception;

    boolean delete(String bankId) throws Exception;
}
