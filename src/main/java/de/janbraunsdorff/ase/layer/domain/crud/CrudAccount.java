package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;

import java.util.Collections;
import java.util.List;

public class CrudAccount implements ICrudAccount {
    private final CrudAccountRepository repo;

    public CrudAccount(CrudAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<AccountMemoryEntity> getAccountsOfBank(String bankId) {
        try {
            return this.repo.getAccountsOfBankByBankId(bankId);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<AccountMemoryEntity> getAccountsOfBankByAcronym(String acronym) {
        try {
            return this.repo.getAccountsOfBankByBankAcronym(acronym);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public AccountMemoryEntity createAccount(String id, AccountMemoryEntity account) {
        try {
            return this.repo.createAccountByBankId(id, account);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return new AccountMemoryEntity("----", "----", "----");
        }
    }

    @Override
    public AccountMemoryEntity createAccountByAcronym(String acronym, AccountMemoryEntity account) {
        try {
            return this.repo.createAccountByBankAcronym(acronym, account);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return new AccountMemoryEntity("----", "----", "----");
        }
    }

    @Override
    public void deleteById(String id) {
        try {
             this.repo.deleteAccountById(id);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void deleteByAcronym(String acronym) {
        try {
             this.repo.deleteAccountByAcronym(acronym);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return;
        }
    }
}
