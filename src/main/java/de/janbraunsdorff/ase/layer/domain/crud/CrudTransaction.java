package de.janbraunsdorff.ase.layer.domain.crud;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.TransactionMemoryRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.List;

public class CrudTransaction implements ICrudTransaction{
    private final TransactionMemoryRepository transactionRepo;

    public CrudTransaction(TransactionMemoryRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public void createTransactionByAccountId(String id, TransactionMemoryEntity entity) {

    }

    public List<Transaction> getAllTransactionOfAccount(String acronym) throws AccountNotFoundException {
        return this.transactionRepo.getTransactionByAccountAcronym(acronym);
    }
}
