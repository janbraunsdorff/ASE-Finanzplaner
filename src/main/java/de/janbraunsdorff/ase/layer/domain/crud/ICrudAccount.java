package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;

import java.util.List;

public interface ICrudAccount {

    List<Account> getAccountsOfBank(String bankId) throws BankNotFoundExecption;

    List<Account> getAccountsOfBankByAcronym(String acronym) throws BankNotFoundExecption;

    Account createAccount(String id, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException;

    Account createAccountByAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException;

    void deleteById(String s) throws AccountNotFoundException;

    void deleteByAcronym(String s) throws AccountNotFoundException;
}
