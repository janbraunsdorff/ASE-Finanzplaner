package de.janbraunsdorff.ase.layer.persistence.repositories;

import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.List;

public interface CrudTransactionRepository {
    TransactionMemoryEntity createTransactionByAccountAcronym(String id, TransactionMemoryEntity entity) throws AccountNotFoundException;
    TransactionMemoryEntity createTransactionByAccountId(String id, TransactionMemoryEntity entity) throws AccountNotFoundException;

    List<TransactionMemoryEntity> getTransactionByAccountId(String id) throws AccountNotFoundException;
    List<TransactionMemoryEntity> getTransactionByAccountAcronym(String acronym) throws AccountNotFoundException;

    void deleteTransactionById(String acronym) throws TransactionNotFoundException;
}
