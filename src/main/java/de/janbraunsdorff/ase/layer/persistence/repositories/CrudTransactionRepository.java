package de.janbraunsdorff.ase.layer.persistence.repositories;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;

import java.util.List;

public interface CrudTransactionRepository {
    Transaction createTransactionByAccountAcronym(String id, Transaction entity) throws AccountNotFoundException;
    Transaction createTransactionByAccountId(String id, Transaction entity) throws AccountNotFoundException;

    List<Transaction> getTransactionByAccountId(String id) throws AccountNotFoundException;
    List<Transaction> getTransactionByAccountAcronym(String acronym) throws AccountNotFoundException;

    void deleteTransactionById(String acronym) throws TransactionNotFoundException;
}
