package de.janbraunsdorff.ase.layer.domain.crud;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.List;

public interface ICrudTransaction {
    void createTransactionByAccountId(String id, TransactionMemoryEntity entity);
    List<Transaction> getAllTransactionOfAccount(String acronym) throws AccountNotFoundException;
}
