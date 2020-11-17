package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudBankRepository;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;

import java.util.List;

public class CrudBank implements ICrudBank {

    private final CrudBankRepository repo;

    public CrudBank(CrudBankRepository repo) {
        this.repo = repo;
    }

    @Override
    public Bank get(String id) throws BankNotFoundExecption {
        return this.repo.getBanks(id);
    }

    @Override
    public List<Bank> get() throws BankNotFoundExecption {
        return this.repo.getBanks();
    }

    @Override
    public Bank create(Bank entity) throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        this.repo.createBank(entity);
        return entity;
    }


    @Override
    public void deleteByAcronym(String id) throws BankNotFoundExecption {
        this.deleteById(this.repo.getBankByAcronym(id).getId());
    }


    @Override
    public void deleteById(String id) {
        this.repo.deleteBankById(id);
    }

    @Override
    public Bank getByAcronym(String acronym) throws BankNotFoundExecption {
        return this.repo.getBankByAcronym(acronym);
    }
}
