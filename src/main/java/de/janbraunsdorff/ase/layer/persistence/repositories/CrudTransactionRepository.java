package de.janbraunsdorff.ase.layer.persistence.repositories;

import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;

public interface CrudTransactionRepository {
    TransactionEntity createTransactionByAcronym(String id, TransactionEntity entity);
}
