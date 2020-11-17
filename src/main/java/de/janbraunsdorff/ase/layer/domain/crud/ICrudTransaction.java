package de.janbraunsdorff.ase.layer.domain.crud;

import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

public interface ICrudTransaction {
    void createTransactionByAccountId(String id, TransactionMemoryEntity entity);
}
