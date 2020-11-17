package de.janbraunsdorff.ase.layer.persistence.repositories;

import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;

import java.util.List;

public interface CrudTransactionRepository {
    TransactionEntity createTransactionByAccountAcronym(String id, TransactionEntity entity) throws AccountNotFoundException;
    TransactionEntity createTransactionByAccountId(String id, TransactionEntity entity) throws AccountNotFoundException;

    List<TransactionEntity> getTransactionByAccountId(String id) throws AccountNotFoundException;
    List<TransactionEntity> getTransactionByAccountAcronym(String acronym) throws AccountNotFoundException;

    void deleteTransactionById(String acronym) throws TransactionNotFoundException;
}
