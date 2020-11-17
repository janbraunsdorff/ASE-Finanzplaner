package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudAccountRepository;

import java.util.Collections;
import java.util.List;

public class CrudAccount implements ICrudAccount {
    private final CrudAccountRepository repo;

    public CrudAccount(CrudAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Account> getAccountsOfBank(String bankId) {
        try {
            return this.repo.getAccountsOfBankByBankId(bankId);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Account> getAccountsOfBankByAcronym(String acronym) {
        try {
            return this.repo.getAccountsOfBankByBankAcronym(acronym);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Account createAccount(String id, Account account) {
        try {
            return this.repo.createAccountByBankId(id, account);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return new Account("----", "----", "----");
        }
    }

    @Override
    public Account createAccountByAcronym(String acronym, Account account) {
        try {
            return this.repo.createAccountByBankAcronym(acronym, account);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception e) {
            return new Account("----", "----", "----");
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
