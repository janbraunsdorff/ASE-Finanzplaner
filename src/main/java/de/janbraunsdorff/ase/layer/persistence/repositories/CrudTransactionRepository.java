package de.janbraunsdorff.ase.layer.persistence.repositories;

import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;

import java.util.List;

public interface CrudTransactionRepository {
    TransactionEntity createTransactionByAccountAcronym(String id, TransactionEntity entity) throws Exception;
    TransactionEntity createTransactionByAccountId(String id, TransactionEntity entity) throws Exception;

    List<TransactionEntity> getTransactionByAccountId(String id) throws Exception;
    List<TransactionEntity> getTransactionByAccountAcronym(String acronym) throws Exception;
}
