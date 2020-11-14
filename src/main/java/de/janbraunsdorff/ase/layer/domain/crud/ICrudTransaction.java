package de.janbraunsdorff.ase.layer.domain.crud;

import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;

public interface ICrudTransaction {
    void createTransactionByAccountId(String id, TransactionEntity entity);
}
