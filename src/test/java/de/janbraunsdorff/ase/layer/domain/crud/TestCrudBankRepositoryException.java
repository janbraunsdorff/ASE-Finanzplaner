package de.janbraunsdorff.ase.layer.domain.crud;

import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.util.List;

public class TestCrudBankRepositoryException implements CrudBankRepository {
    @Override
    public BankEntity get(String Id) throws Exception {
        throw new Exception();
    }

    @Override
    public List<BankEntity> get() throws Exception {
        throw new Exception();
    }

    @Override
    public BankEntity getByAcronym(String acronym) {
        return null;
    }

    @Override
    public BankEntity create(BankEntity bankEntity) throws Exception {
        throw new Exception();
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws Exception {
        throw new Exception();
    }

    @Override
    public boolean delete(String bankId) throws Exception {
        throw new Exception();
    }
}
