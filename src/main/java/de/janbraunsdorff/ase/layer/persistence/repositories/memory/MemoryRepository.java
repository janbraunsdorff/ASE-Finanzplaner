package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class MemoryRepository {
    protected final Map<String, BankMemoryEntity> memory = new HashMap<>();

    protected Transaction convertToDomain(TransactionMemoryEntity entity){
        return new Transaction(
                entity.getValue(),
                entity.getDate(),
                entity.getThirdParty(),
                entity.getCategory(),
                entity.getContract(),
                entity.getIndex()
        );
    }

    protected Account convertToDomain(AccountMemoryEntity entity) {
        return new Account(
                entity.getName(),
                entity.getNumber(),
                new ArrayList<>(entity.getTransactionEntities().stream().map(this::convertToDomain).collect(Collectors.toList())),
                entity.getAcronym()
        );
    }

    protected Bank convertToDomain(BankMemoryEntity entity){
        return new Bank(
                entity.getName(),
                new ArrayList<>(entity.getAccounts().stream().map(this::convertToDomain).collect(Collectors.toList())),
                entity.getAcronym()
        );
    }

    protected AccountMemoryEntity convertToEntity(Account account){
        return new AccountMemoryEntity(
                UUID.randomUUID().toString(),
                account.getName(),
                account.getNumber(),
                account.getAcronym()
        );
    }

    protected BankMemoryEntity convertToEntity(Bank bank){
        return new BankMemoryEntity(
                UUID.randomUUID().toString(),
                bank.getName(),
                bank.getAcronym()
        );
    }

    protected TransactionMemoryEntity convertToEntity(Transaction transaction, Integer index){
        return new TransactionMemoryEntity(
                UUID.randomUUID().toString(),
                transaction.getValue(),
                transaction.getThirdParty(),
                transaction.getCategory(),
                transaction.getContract(),
                index
        );
    }
}
