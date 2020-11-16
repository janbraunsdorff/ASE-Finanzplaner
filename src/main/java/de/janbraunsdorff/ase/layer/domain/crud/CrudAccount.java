package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.Collections;
import java.util.List;

public class CrudAccount implements ICrudAccount {
    private final CrudAccountRepository repo;

    public CrudAccount(CrudAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<AccountEntity> getAccountsOfBank(String bankId) {
        try {
            return this.repo.getAccountsOfBankById(bankId);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<AccountEntity> getAccountsOfBankByAcronym(String acronym) {
        try {
            return this.repo.getAccountsOfBankByAcronym(acronym);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public AccountEntity createAccount(String id, AccountEntity account) {
        try {
            return this.repo.createAccountByBankId(id, account);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return new AccountEntity("----", "----", "----");
        }
    }

    @Override
    public AccountEntity createAccountByAcronym(String acronym, AccountEntity account) {
        try {
            return this.repo.createByAcronym(acronym, account);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return new AccountEntity("----", "----", "----");
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
