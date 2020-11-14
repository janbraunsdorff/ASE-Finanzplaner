package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecution;
import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.Collections;
import java.util.List;

public class CrudBank implements ICrudBank {

    private final CrudBankRepository repo;
    private final BankEntity defaultEntity;

    public CrudBank(CrudBankRepository repo) {
        this.repo = repo;
        this.defaultEntity = new BankEntity("----", "----", "----");

    }

    @Override
    public BankEntity get(String id) {
        try {
            return this.repo.getBanks(id);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity;
        }
    }

    @Override
    public List<BankEntity> get() {
        try {
            return this.repo.getBanks();

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public BankEntity create(BankEntity entity) {
        try {
            this.repo.createBank(entity);
            return entity;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity;
        }
    }


    @Override
    public BankEntity update(BankEntity entity) {
        try {
            return this.repo.update(entity);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity;
        }
    }

    @Override
    public boolean deleteByAcronym(String id) {
        try {
            return this.deleteById(this.repo.getBankByAcronym(id).getId());
        } catch (BankNotFoundExecution bankNotFoundExecution) {
            bankNotFoundExecution.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteById(String id) {
        try {
            return this.repo.delete(id);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BankEntity getByAcronym(String acronym) {
        try {
            return this.repo.getBankByAcronym(acronym);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity;
        }
    }
}
