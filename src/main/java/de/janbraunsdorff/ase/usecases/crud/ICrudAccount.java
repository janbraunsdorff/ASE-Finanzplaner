package de.janbraunsdorff.ase.usecases.crud;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;

import java.util.List;

public interface ICrudAccount {
    AccountEntity getAccountById(String id);
    List<AccountEntity> getAccountsOfBank(String bankId);
}
