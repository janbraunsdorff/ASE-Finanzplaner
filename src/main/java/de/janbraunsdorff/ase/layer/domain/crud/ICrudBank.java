package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.List;

public interface ICrudBank {
    BankMemoryEntity get(String id);

    List<BankMemoryEntity> get();

    BankMemoryEntity create(BankMemoryEntity entity);

    void deleteByAcronym(String id);

    void deleteById(String id);

    BankMemoryEntity getByAcronym(String acronym);
}
