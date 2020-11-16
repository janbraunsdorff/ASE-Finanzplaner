package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.*;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.TransactionEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class MemoryRepository implements CrudBankRepository, CrudAccountRepository, CrudTransactionRepository {

    private final Map<String, BankEntity> memory = new HashMap<>();

    @Override
    public BankEntity getBanks(String id) throws BankNotFoundExecption {
        if (!this.memory.containsKey(id)) {
            throw new BankNotFoundExecption(id);
        }
        return this.memory.get(id);
    }

    public BankEntity getBankByAcronym(String acronym) throws BankNotFoundExecption {
        Optional<BankEntity> first = this.memory.values().stream().
                filter(s -> s.getAcronym().equals(acronym)).
                findFirst();

        if (!first.isPresent()) {
            throw new BankNotFoundExecption(acronym);
        }
        return first.get();
    }

    @Override
    public List<BankEntity> getBanks() {
        return new ArrayList<>(this.memory.values());
    }

    @Override
    public void createBank(BankEntity bankEntity) throws Exception {
        if (this.memory.containsKey(bankEntity.getId())) {
            throw new IdAlreadyExitsException(bankEntity.getId());
        }

        Optional<BankEntity> first = this.memory.values()
                .stream()
                .filter(e -> e.getAcronym().equals(bankEntity.getAcronym()))
                .findFirst();

        if (first.isPresent()) {
            throw new AcronymAlreadyExistsException(bankEntity.getAcronym());
        }

        this.memory.put(bankEntity.getId(), bankEntity);
    }

    @Override
    public void deleteBankById(String bankId) {
        this.memory.remove(bankId);
    }

    @Override
    public void deleteBankByAcronym(String acronym) {
        Optional<BankEntity> entity = this.memory.values().stream()
                .filter(s -> s.getAcronym().equals(acronym))
                .findFirst();

        entity.ifPresent(bankEntity -> this.deleteBankById(bankEntity.getId()));
    }

    // --------------------------------
    // Account Repo
    // --------------------------------
    @Override
    public AccountEntity createAccountByBankId(String bankId, AccountEntity entity) throws Exception {
        if (!this.memory.containsKey(bankId)) {
            throw new BankNotFoundExecption(bankId);
        }

        Optional<AccountEntity> first = this.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .filter(a -> a.getAcronym().equals(entity.getAcronym()))
                .findFirst();

        if (first.isPresent()) {
            throw new AcronymAlreadyExistsException(entity.getAcronym());
        }

        this.memory.get(bankId).addAccount(entity);
        return entity;
    }

    @Override
    public AccountEntity createAccountByBankAcronym(String acronym, AccountEntity account) throws Exception {
        String id = this.getBankByAcronym(acronym).getId();
        return this.createAccountByBankId(id, account);
    }

    @Override
    public List<AccountEntity> getAccountsOfBankByBankId(String bankId) throws Exception {
        if (!this.memory.containsKey(bankId)) {
            throw new BankNotFoundExecption(bankId);
        }
        return new ArrayList<>(this.memory.get(bankId).getAccounts());
    }

    @Override
    public List<AccountEntity> getAccountsOfBankByBankAcronym(String bankAcronym) throws Exception {
        Optional<BankEntity> bank = this.memory.values().stream().filter(s -> s.getAcronym().equals(bankAcronym)).findFirst();
        if (!bank.isPresent()) {
            throw new BankNotFoundExecption(bankAcronym);
        }

        return new ArrayList<>(bank.get().getAccounts());
    }

    @Override
    public void deleteAccountByAcronym(String acronym) throws Exception {
        deleteAccount(a -> a.getAcronym().equals(acronym), acronym);
    }

    @Override
    public void deleteAccountById(String id) throws Exception {
        deleteAccount(a -> a.getId().equals(id), id);
    }

    private void deleteAccount(Predicate<AccountEntity> predicate, String key) throws Exception {
        AtomicBoolean found = new AtomicBoolean(false);
        this.memory.values().forEach(bank -> {
            Optional<AccountEntity> first = bank.getAccounts()
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
    public TransactionEntity createTransactionByAccountAcronym(String acronym, TransactionEntity entity) throws Exception {
        return createTransaction(acronym, entity, a -> a.getAcronym().equals(acronym));
    }

    @Override
    public TransactionEntity createTransactionByAccountId(String id, TransactionEntity entity) throws Exception {
        return createTransaction(id, entity, a -> a.getId().equals(id));
    }

    @Override
    public List<TransactionEntity> getTransactionByAccountId(String id) throws Exception{
        return getTransactions(id, a -> a.getId().equals(id));
    }

    @Override
    public List<TransactionEntity> getTransactionByAccountAcronym(String acronym) throws Exception{
       return getTransactions(acronym, a -> a.getAcronym().equals(acronym));
    }

    private List<TransactionEntity> getTransactions(String key, Predicate<AccountEntity> predicate) throws AccountNotFoundException {
        Optional<AccountEntity> account = this.memory.values()
                .stream()
                .flatMap(b -> b.getAccounts().stream())
                .filter(predicate)
                .findFirst();

        if (!account.isPresent()){
            throw new AccountNotFoundException(key);
        }
        return new ArrayList<>(account.get().getTransactionEntities());
    }


    private TransactionEntity createTransaction(String key, TransactionEntity entity, Predicate<AccountEntity> predicate) throws Exception {
        Optional<AccountEntity> account = this.memory.values().stream()
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
