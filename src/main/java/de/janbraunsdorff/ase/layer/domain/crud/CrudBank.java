package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.BankNotFoundExecption;
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
    public void deleteByAcronym(String id) {
        try {
            this.deleteById(this.repo.getBankByAcronym(id).getId());
        } catch (BankNotFoundExecption bankNotFoundExecution) {
            bankNotFoundExecution.printStackTrace();
        }
    }


    @Override
    public void deleteById(String id) {
        try {
            this.repo.deleteBankById(id);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return;
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
