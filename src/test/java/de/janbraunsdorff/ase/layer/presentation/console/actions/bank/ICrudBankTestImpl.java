package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;

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
    public Bank create(Bank entity) throws AcronymAlreadyExistsException {
        this.createBank = entity;
        return entity;
    }

    @Override
    public void deleteByAcronym(String id) throws BankNotFoundExecption {
        this.deleteAcronym = id;
    }
}
