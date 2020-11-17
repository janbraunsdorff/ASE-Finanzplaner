package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudBankRepository;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.Collections;
import java.util.List;

public class CrudBank implements ICrudBank {

    private final CrudBankRepository repo;
    private final BankMemoryEntity defaultEntity;

    public CrudBank(CrudBankRepository repo) {
        this.repo = repo;
        this.defaultEntity = new BankMemoryEntity("----", "----", "----");

    }

    @Override
    public Bank get(String id) {
        try {
            return this.repo.getBanks(id);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity.convertToDomainEntity();
        }
    }

    @Override
    public List<Bank> get() {
        try {
            return this.repo.getBanks();

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Bank create(Bank entity) {
        try {
            this.repo.createBank(entity);
            return entity;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity.convertToDomainEntity();
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
    public Bank getByAcronym(String acronym) {
        try {
            return this.repo.getBankByAcronym(acronym);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return this.defaultEntity.convertToDomainEntity();
        }
    }
}
