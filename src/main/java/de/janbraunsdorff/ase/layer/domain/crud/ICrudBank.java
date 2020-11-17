package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;

import java.util.List;

public interface ICrudBank {
    Bank get(String id);

    List<Bank> get();

    Bank create(Bank entity);

    void deleteByAcronym(String id);

    void deleteById(String id);

    Bank getByAcronym(String acronym);
}
