package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;

import java.util.Collections;
import java.util.List;

public class ICrudBankTestImpl implements ICrudBank {
    protected String deleteAcronym;
    protected Bank createBank;
    protected boolean isGetCalled;
    @Override
    public Bank get(String id) throws BankNotFoundExecption {
        return null;
    }

    @Override
    public List<Bank> get() throws BankNotFoundExecption {
        this.isGetCalled = true;
        return Collections.emptyList();
    }

    @Override
    public Bank create(Bank entity) throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        this.createBank = entity;
        return entity;
    }

    @Override
    public void deleteByAcronym(String id) throws BankNotFoundExecption {
        this.deleteAcronym = id;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Bank getByAcronym(String acronym) throws BankNotFoundExecption {
        return null;
    }
}
