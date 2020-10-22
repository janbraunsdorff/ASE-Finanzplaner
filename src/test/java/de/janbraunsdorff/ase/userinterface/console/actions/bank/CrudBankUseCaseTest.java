package de.janbraunsdorff.ase.userinterface.console.actions.bank;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudBank;

import java.util.List;

public class CrudBankUseCaseTest implements ICrudBank {

    protected String name;
    protected String acronym;

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
        return entity;
    }

    @Override
    public BankEntity update(BankEntity entity) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}