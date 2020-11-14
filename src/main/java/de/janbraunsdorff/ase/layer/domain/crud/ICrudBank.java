package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public interface ICrudBank {
    BankEntity get(String id);

    List<BankEntity> get();

    BankEntity create(BankEntity entity);

    void deleteByAcronym(String id);

    void deleteById(String id);

    BankEntity getByAcronym(String acronym);
}
