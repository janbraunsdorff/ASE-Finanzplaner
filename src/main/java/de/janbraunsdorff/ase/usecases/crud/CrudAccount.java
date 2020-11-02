package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;

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
            BankEntity entity = this.bankRepository.get(id);
            entity.getAccounts().add(account);
            return account;
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
            BankEntity entity = this.bankRepository.getByAcronym(acronym);
            entity.getAccounts().add(account);
            return account;
        }catch (IllegalArgumentException ex){
            throw ex;
        }
        catch (Exception e) {
            return new AccountEntity("----", "----", "----", 0, Collections.emptyList());
        }
    }
}
