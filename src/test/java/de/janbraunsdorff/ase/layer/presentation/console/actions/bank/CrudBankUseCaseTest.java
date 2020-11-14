package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudBank;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.List;

public class CrudBankUseCaseTest implements ICrudBank {

    protected String name;
    protected String acronym;
    protected String id;

    @Override
    public BankEntity get(String id) {
        return null;
    }

    @Override
    public List<BankEntity> get() {
        return null;
    }

    @Override
    public BankEntity create(BankEntity entity) {
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
    public BankEntity getByAcronym(String acronym) {
        return null;
    }
}
