package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.util.Arrays;
import java.util.List;

import static de.janbraunsdorff.ase.usecases.crud.CrudBankTest.defaultBankEntity;

public class TestCrudBankRepositoryFine implements CrudBankRepository {

    @Override
    public BankEntity get(String Id) throws Exception {
        return defaultBankEntity;
    }

    @Override
    public List<BankEntity> get() throws Exception {
        return Arrays.asList(defaultBankEntity);
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
