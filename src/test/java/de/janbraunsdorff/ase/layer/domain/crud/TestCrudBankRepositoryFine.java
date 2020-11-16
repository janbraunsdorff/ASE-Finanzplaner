package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.Collections;
import java.util.List;

import static de.janbraunsdorff.ase.layer.domain.crud.CrudBankTest.defaultBankEntity;

public class TestCrudBankRepositoryFine implements CrudBankRepository {

    @Override
    public BankEntity getBanks(String Id) throws BankNotFoundExecption {
        return defaultBankEntity;
    }

    @Override
    public List<BankEntity> getBanks() {
        return Collections.singletonList(defaultBankEntity);
    }

    @Override
    public BankEntity getBankByAcronym(String acronym) {
        return null;
    }

    @Override
    public void createBank(BankEntity bankEntity) throws Exception {
    }



    @Override
    public void deleteBankById(String bankId) {
    }

    @Override
    public void deleteBankByAcronym(String bankId) {

    }
}
