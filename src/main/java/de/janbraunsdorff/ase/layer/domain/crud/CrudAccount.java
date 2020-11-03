package de.janbraunsdorff.ase.layer.domain.crud;


import de.janbraunsdorff.ase.layer.persistence.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;

import java.util.Collections;
import java.util.List;

public class CrudAccount implements  ICrudAccount{
    private final CrudAccountRepository repo;
    private final CrudBankRepository bankRepository;

    public CrudAccount(CrudAccountRepository repo, CrudBankRepository bankRepository) {
        this.repo = repo;
        this.bankRepository = bankRepository;
    }

    @Override
    public AccountEntity getAccountById(String id) {
        try {
            return this.repo.getAccountById(id);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return new AccountEntity("----", "----", "----", 0, Collections.emptyList());
        }
    }

    @Override
    public List<AccountEntity> getAccountsOfBank(String bankId) {
        try {
            return this.repo.getAccountsOfBank(bankId);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<AccountEntity> getAccountsOfBankByAcronym(String acronym) {
        try {
            return this.repo.getAccountsByAcronym(acronym);
        }
        catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public AccountEntity createAccount(String id, AccountEntity account) {
        try {
            return this.repo.create(id, account);
        }catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return new AccountEntity("----", "----", "----", 0, Collections.emptyList());
        }
    }

    @Override
    public AccountEntity createAccountByAcronym(String acronym, AccountEntity account) {
        try {
            return this.repo.createByAcronym(acronym, account);
        }catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return new AccountEntity("----", "----", "----", 0, Collections.emptyList());
        }
    }
}
