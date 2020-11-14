package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecution;
import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public class TestCrudBankRepositoryException implements CrudBankRepository {
    @Override
    public BankEntity getBanks(String Id) throws BankNotFoundExecution {
        throw new BankNotFoundExecution("");
    }

    @Override
    public List<BankEntity> getBanks() {
        return null;
    }

    @Override
    public BankEntity getBankByAcronym(String acronym) {
        return null;
    }

    @Override
    public void createBank(BankEntity bankEntity) throws Exception {
        throw new Exception();
    }



    @Override
    public boolean delete(String bankId) throws Exception {
        throw new Exception();
    }
}
