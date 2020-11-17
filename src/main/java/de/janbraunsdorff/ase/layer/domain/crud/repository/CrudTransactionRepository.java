package de.janbraunsdorff.ase.layer.domain.crud.repository;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.TransactionNotFoundException;

import java.util.List;

public interface CrudTransactionRepository {
    Transaction createTransactionByAccountAcronym(String id, Transaction entity) throws AccountNotFoundException;
    Transaction createTransactionByAccountId(String id, Transaction entity) throws AccountNotFoundException;

    List<Transaction> getTransactionByAccountId(String id) throws AccountNotFoundException;
    List<Transaction> getTransactionByAccountAcronym(String acronym) throws AccountNotFoundException;

    void deleteTransactionById(String acronym) throws TransactionNotFoundException;
}
