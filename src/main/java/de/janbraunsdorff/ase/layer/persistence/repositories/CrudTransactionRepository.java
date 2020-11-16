package de.janbraunsdorff.ase.layer.persistence.repositories;

import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;

public interface CrudTransactionRepository {
    TransactionEntity createTransactionByAccountAcronym(String id, TransactionEntity entity) throws Exception;
    TransactionEntity createTransactionByAccountId(String id, TransactionEntity entity) throws Exception;
}
