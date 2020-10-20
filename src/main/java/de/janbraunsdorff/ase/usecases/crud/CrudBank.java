package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.util.Collections;
import java.util.List;

public class CrudBank {

    private final CrudBankRepository repo;
    private final BankEntity defaultEntity;

    public CrudBank(CrudBankRepository repo) {
        this.repo = repo;
        this.defaultEntity = new BankEntity("----", "----", Collections.emptyList());

    }

    public BankEntity get(String id) {
        try {
            return this.repo.get(id);
        } catch (Exception e) {
            return this.defaultEntity;
        }
    }

    public List<BankEntity> get() {
        try {
            return this.repo.get();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public BankEntity create(BankEntity entity) {
        try {
            return this.repo.create(entity);
        } catch (Exception e) {
            return this.defaultEntity;
        }
    }


    public BankEntity update(BankEntity entity) {
        try {
            return this.repo.update(entity);
        } catch (Exception e) {
            return defaultEntity;
        }
    }


    public boolean delete(String id) {
        try {
            return this.repo.delete(id);
        } catch (Exception e) {
            return false;
        }
    }
}
