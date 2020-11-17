package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.persistence.repositories.*;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.TransactionMemoryEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemoryRepository implements CrudBankRepository, CrudAccountRepository, CrudTransactionRepository {

    private final Map<String, BankMemoryEntity> memory = new HashMap<>();

    @Override
    public Bank getBanks(String id) throws BankNotFoundExecption {
        if (!this.memory.containsKey(id)) {
            throw new BankNotFoundExecption(id);
        }
        return this.memory.get(id).convertToDomainEntity();
    }

    public Bank getBankByAcronym(String acronym) throws BankNotFoundExecption {
        Optional<BankMemoryEntity> first = this.memory.values().stream().
                filter(s -> s.getAcronym().equals(acronym)).
                findFirst();

        if (!first.isPresent()) {
            throw new BankNotFoundExecption(acronym);
        }
        return first.get().convertToDomainEntity();
    }

    @Override
    public List<Bank> getBanks() {
        return this.memory.values().stream().map(BankMemoryEntity::convertToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public void createBank(Bank bankEntity) throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        if (this.memory.containsKey(bankEntity.getId())) {
            throw new IdAlreadyExitsException(bankEntity.getId());
        }

        Optional<BankMemoryEntity> first = this.memory.values()
                .stream()
                .filter(e -> e.getAcronym().equals(bankEntity.getAcronym()))
                .findFirst();

        if (first.isPresent()) {
            throw new AcronymAlreadyExistsException(bankEntity.getAcronym());
        }

        this.memory.put(bankEntity.getId(), new BankMemoryEntity(bankEntity));
    }

    @Override
    public void deleteBankById(String bankId) {
        this.memory.remove(bankId);
    }

    @Override
    public void deleteBankByAcronym(String acronym) {
        Optional<BankMemoryEntity> entity = this.memory.values().stream()
                .filter(s -> s.getAcronym().equals(acronym))
                .findFirst();

        entity.ifPresent(bankEntity -> this.deleteBankById(bankEntity.getId()));
    }

    // --------------------------------
    // Account Repo
    // --------------------------------
    @Override
    public Account createAccountByBankId(String bankId, Account entity) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        if (!this.memory.containsKey(bankId)) {
            throw new BankNotFoundExecption(bankId);
        }

        Optional<AccountMemoryEntity> first = this.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .filter(a -> a.getAcronym().equals(entity.getAcronym()))
                .findFirst();

        if (first.isPresent()) {
            throw new AcronymAlreadyExistsException(entity.getAcronym());
        }

        this.memory.get(bankId).addAccount(new AccountMemoryEntity(entity));
        return entity;
    }

    @Override
    public Account createAccountByBankAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        String id = this.getBankByAcronym(acronym).getId();
        return this.createAccountByBankId(id, account);
    }

    @Override
    public List<Account> getAccountsOfBankByBankId(String bankId) throws BankNotFoundExecption {
        if (!this.memory.containsKey(bankId)) {
            throw new BankNotFoundExecption(bankId);
        }
        return this.memory.get(bankId).getAccounts().stream().map(AccountMemoryEntity::convertToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Account> getAccountsOfBankByBankAcronym(String bankAcronym) throws BankNotFoundExecption {
        Optional<BankMemoryEntity> bank = this.memory.values().stream().filter(s -> s.getAcronym().equals(bankAcronym)).findFirst();
        if (!bank.isPresent()) {
            throw new BankNotFoundExecption(bankAcronym);
        }

        return bank.get().getAccounts().stream().map(AccountMemoryEntity::convertToDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountByAcronym(String acronym) throws AccountNotFoundException {
        deleteAccount(a -> a.getAcronym().equals(acronym), acronym);
    }

    @Override
    public void deleteAccountById(String id) throws AccountNotFoundException {
        deleteAccount(a -> a.getId().equals(id), id);
    }

    private void deleteAccount(Predicate<AccountMemoryEntity> predicate, String key) throws AccountNotFoundException {
        AtomicBoolean found = new AtomicBoolean(false);
        this.memory.values().forEach(bank -> {
            Optional<AccountMemoryEntity> first = bank.getAccounts()
                    .stream()
                    .filter(predicate)
                    .findFirst();


            first.ifPresent(e -> {
                bank.removeAccount(e.getId());
                found.set(true);
            });
        });

        if (!found.get()) {
            throw new AccountNotFoundException(key);
        }
    }

    // --------------------
    // Transaction repo
    @Override
    public TransactionMemoryEntity createTransactionByAccountAcronym(String acronym, TransactionMemoryEntity entity) throws AccountNotFoundException {
        return createTransaction(acronym, entity, a -> a.getAcronym().equals(acronym));
    }

    @Override
    public TransactionMemoryEntity createTransactionByAccountId(String id, TransactionMemoryEntity entity) throws AccountNotFoundException {
        return createTransaction(id, entity, a -> a.getId().equals(id));
    }

    @Override
    public List<TransactionMemoryEntity> getTransactionByAccountId(String id) throws AccountNotFoundException {
        return getTransactions(id, a -> a.getId().equals(id));
    }

    @Override
    public List<TransactionMemoryEntity> getTransactionByAccountAcronym(String acronym) throws AccountNotFoundException {
        return getTransactions(acronym, a -> a.getAcronym().equals(acronym));
    }

    @Override
    public void deleteTransactionById(String acronym) throws TransactionNotFoundException{
        AtomicBoolean found = new AtomicBoolean(false);
        this.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .forEach(acc -> {
                    Optional<TransactionMemoryEntity> first = acc.getTransactionEntities()
                            .stream()
                            .filter(a -> a.getId().equals(acronym))
                            .findFirst();

                    first.ifPresent( e -> {
                        acc.removeTransaction(acronym);
                        found.set(true);
                    });
                });

        if (!found.get()){
            throw new TransactionNotFoundException(acronym);
        }



    }



    private List<TransactionMemoryEntity> getTransactions(String key, Predicate<AccountMemoryEntity> predicate) throws AccountNotFoundException {
        Optional<AccountMemoryEntity> account = this.memory.values()
                .stream()
                .flatMap(b -> b.getAccounts().stream())
                .filter(predicate)
                .findFirst();

        if (!account.isPresent()) {
            throw new AccountNotFoundException(key);
        }
        return new ArrayList<>(account.get().getTransactionEntities());
    }


    private TransactionMemoryEntity createTransaction(String key, TransactionMemoryEntity entity, Predicate<AccountMemoryEntity> predicate) throws AccountNotFoundException {
        Optional<AccountMemoryEntity> account = this.memory.values().stream()
                .flatMap(b -> b.getAccounts().stream())
                .filter(predicate)
                .findFirst();

        if (!account.isPresent()) {
            throw new AccountNotFoundException(key);
        }

        account.get().addTransaction(entity);
        return entity;
    }



}
