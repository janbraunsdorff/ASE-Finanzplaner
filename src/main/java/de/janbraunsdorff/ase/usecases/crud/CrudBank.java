package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;

import java.util.Collections;
import java.util.List;

public class CrudBank implements ICrudBank {

    private final CrudBankRepository repo;
    private final BankEntity defaultEntity;

    public CrudBank(CrudBankRepository repo) {
        this.repo = repo;
        this.defaultEntity = new BankEntity("----", "----", Collections.emptyList(), "--");

    }

    @Override
    public BankEntity get(String id) {
        try {
            return this.repo.get(id);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return this.defaultEntity;
        }
    }

    @Override
    public List<BankEntity> get() {
        try {
            return this.repo.get();

        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public BankEntity create(BankEntity entity) {
        try {
            return this.repo.create(entity);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return this.defaultEntity;
        }
    }


    @Override
    public BankEntity update(BankEntity entity) {
        try {
            return this.repo.update(entity);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return this.defaultEntity;
        }
    }

    @Override
    public boolean deleteByAcronym(String id) {
         return this.deleteById(this.repo.getByAcronym(id).getId());
    }


    @Override
    public boolean deleteById(String id) {
        try {
            return this.repo.delete(id);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return false;
        }
    }
}
