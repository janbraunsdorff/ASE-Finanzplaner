package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.Collections;
import java.util.List;

import static de.janbraunsdorff.ase.layer.domain.crud.CrudBankTest.defaultBankEntity;

public class TestCrudBankRepositoryFine implements CrudBankRepository {

    @Override
    public BankEntity getBankById(String Id) throws Exception {
        return defaultBankEntity;
    }

    @Override
    public List<BankEntity> getBankById() throws Exception {
        return Collections.singletonList(defaultBankEntity);
    }

    @Override
    public BankEntity getBankByAcronym(String acronym) {
        return null;
    }

    @Override
    public BankEntity create(BankEntity bankEntity) throws Exception {
        return defaultBankEntity;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws Exception {
        return defaultBankEntity;
    }

    @Override
    public boolean delete(String bankId) throws Exception {
        return true;
    }
}
