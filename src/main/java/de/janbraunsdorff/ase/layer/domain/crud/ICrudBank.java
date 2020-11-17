package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.List;

public interface ICrudBank {
    Bank get(String id);

    List<Bank> get();

    BankMemoryEntity create(BankMemoryEntity entity);

    void deleteByAcronym(String id);

    void deleteById(String id);

    Bank getByAcronym(String acronym);
}
