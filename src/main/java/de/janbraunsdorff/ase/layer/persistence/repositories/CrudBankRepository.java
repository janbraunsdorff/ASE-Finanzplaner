package de.janbraunsdorff.ase.layer.persistence.repositories;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface CrudBankRepository {
    BankEntity get(String Id) throws Exception;
    List<BankEntity> get() throws Exception;
    BankEntity getByAcronym(String acronym);
    BankEntity create(BankEntity bankEntity) throws Exception;
    BankEntity update(BankEntity bankEntity) throws Exception;

    boolean delete(String bankId) throws Exception;
}
