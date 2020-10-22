package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;

import java.util.Collections;
import java.util.List;

public class CrudAccount implements  ICrudAccount{
    private final CrudAccountRepository repo;

    public CrudAccount(CrudAccountRepository repo) {
        this.repo = repo;
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
}
