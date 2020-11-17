package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.List;

public class CrudBankUseCaseTest implements ICrudBank {

    protected String name;
    protected String acronym;
    protected String id;

    @Override
    public BankMemoryEntity get(String id) {
        return null;
    }

    @Override
    public List<BankMemoryEntity> get() {
        return null;
    }

    @Override
    public BankMemoryEntity create(BankMemoryEntity entity) {
        this.name = entity.getName();
        this.acronym = entity.getAcronym();
        this.id = entity.getId();
        return entity;
    }

    @Override
    public void deleteByAcronym(String id) {

    }

    @Override
    public void deleteById(String id) {

    }


    @Override
    public BankMemoryEntity getByAcronym(String acronym) {
        return null;
    }
}
