package de.janbraunsdorff.ase.layer.domain.repository;


import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.BankNotFoundExecption;

import java.util.List;

public interface BankRepository {
    List<Bank> getBank() throws BankNotFoundExecption;

    Bank getBankByAcronym(String acronym) throws BankNotFoundExecption;
    Bank getBank(String Id) throws BankNotFoundExecption;

    void createBank(Bank bankEntity) throws AcronymAlreadyExistsException;

    void deleteBankById(String bankId);
    void deleteBankByAcronym(String bankId);
}
