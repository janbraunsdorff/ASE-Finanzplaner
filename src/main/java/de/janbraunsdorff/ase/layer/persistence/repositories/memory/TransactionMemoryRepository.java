package de.janbraunsdorff.ase.layer.persistence.repositories.memory;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudTransactionRepository;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionMemoryRepository implements CrudTransactionRepository {
    private final MemoryRepository repo;
    private Integer lastIndex = 0;

    public TransactionMemoryRepository(MemoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Transaction createTransactionByAccountAcronym(String acronym, Transaction entity) throws AccountNotFoundException {
        return createTransaction(acronym, entity, a -> a.getAcronym().equals(acronym));
    }

    @Override
    public Transaction createTransactionByAccountId(String id, Transaction entity) throws AccountNotFoundException {
        return createTransaction(id, entity, a -> a.getId().equals(id));
    }

    @Override
    public List<Transaction> getTransactionByAccountId(String id) throws AccountNotFoundException {
        return getTransactions(id, a -> a.getId().equals(id));
    }

    @Override
    public List<Transaction> getTransactionByAccountAcronym(String acronym) throws AccountNotFoundException {
        return getTransactions(acronym, a -> a.getAcronym().equals(acronym));
    }

    @Override
    public void deleteTransactionById(Integer acronym) throws TransactionNotFoundException {
        AtomicBoolean found = new AtomicBoolean(false);
        this.repo.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .forEach(acc -> {
                    Optional<TransactionMemoryEntity> first = acc.getTransactionEntities()
                            .stream()
                            .filter(a -> a.getIndex().equals(acronym))
                            .findFirst();

                    first.ifPresent( e -> {
                        acc.removeTransaction(acronym);
                        found.set(true);
                    });
                });

        if (!found.get()){
            throw new TransactionNotFoundException(acronym.toString());
        }
    }

    private List<Transaction> getTransactions(String key, Predicate<AccountMemoryEntity> predicate) throws AccountNotFoundException {
        Optional<AccountMemoryEntity> account = this.repo.memory.values()
                .stream()
                .flatMap(b -> b.getAccounts().stream())
                .filter(predicate)
                .findFirst();

        if (!account.isPresent()) {
            throw new AccountNotFoundException(key);
        }
        return account.get().getTransactionEntities().stream().map(repo::convertToDomain).collect(Collectors.toList());
    }

    private Transaction createTransaction(String key, Transaction entity, Predicate<AccountMemoryEntity> predicate) throws AccountNotFoundException {
        Optional<AccountMemoryEntity> account = this.repo.memory.values().stream()
                .flatMap(b -> b.getAccounts().stream())
                .filter(predicate)
                .findFirst();

        if (!account.isPresent()) {
            throw new AccountNotFoundException(key);
        }

        account.get().addTransaction(repo.convertToEntity(entity, lastIndex++));
        return entity;
    }
}
