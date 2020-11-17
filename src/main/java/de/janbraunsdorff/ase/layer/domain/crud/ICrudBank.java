package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;

import java.util.List;

public interface ICrudBank {
    Bank get(String id) throws BankNotFoundExecption;

    List<Bank> get() throws BankNotFoundExecption;

    Bank create(Bank entity) throws AcronymAlreadyExistsException, IdAlreadyExitsException;

    void deleteByAcronym(String id) throws BankNotFoundExecption;

}
