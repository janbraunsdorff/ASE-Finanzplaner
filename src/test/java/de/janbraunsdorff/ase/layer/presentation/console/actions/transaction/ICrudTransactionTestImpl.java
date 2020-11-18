package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudTransaction;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.Collections;
import java.util.List;

public class ICrudTransactionTestImpl implements ICrudTransaction {

    protected String getAllAcronym;

    @Override
    public void createTransactionByAccountId(String id, TransactionMemoryEntity entity) {

    }

    @Override
    public List<Transaction> getAllTransactionOfAccount(String acronym) throws AccountNotFoundException {
        this.getAllAcronym = acronym;
        return Collections.emptyList();
    }
}
