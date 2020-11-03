package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.Collections;
import java.util.List;

import static de.janbraunsdorff.ase.layer.domain.crud.CrudBankTest.defaultBankEntity;

public class TestCrudBankRepositoryFine implements CrudBankRepository {

    @Override
    public BankEntity get(String Id) throws Exception {
        return defaultBankEntity;
    }

    @Override
    public List<BankEntity> get() throws Exception {
        return Collections.singletonList(defaultBankEntity);
    }

    @Override
    public BankEntity getByAcronym(String acronym) {
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
