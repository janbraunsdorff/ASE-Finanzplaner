package de.janbraunsdorff.ase.layer.persistence.repositories.memory;


import de.janbraunsdorff.ase.layer.persistence.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;

import java.util.*;

public class MemoryRepository implements CrudBankRepository, CrudAccountRepository {

    private final Map<String, BankEntity> memory = new HashMap<>();

    @Override
    public BankEntity get(String Id) throws Exception {
        return this.memory.get(Id);
    }

    @Override
    public List<BankEntity> get() throws Exception {
        return new ArrayList<>(this.memory.values());
    }

    @Override
    public BankEntity getByAcronym(String acronym) {
        Optional<BankEntity> first = this.memory.values().stream().
                filter(s -> s.getAcronym().equals(acronym)).
                findFirst();

        if (first.isPresent()){
            return first.get();
        }

        throw new IllegalArgumentException("Bank konnte nicht gefunden werden");
    }

    @Override
    public BankEntity create(BankEntity bankEntity) throws Exception {
        if (this.memory.containsKey(bankEntity.getId())){
            throw new IllegalArgumentException("Id already exists");
        }

        Optional<BankEntity> first = this.memory.values()
                .stream()
                .filter(e -> e.getAcronym().equals(bankEntity.getAcronym()))
                .findFirst();

        if (first.isPresent()){
            throw new IllegalArgumentException("Acronym already exists");
        }

        this.memory.put(bankEntity.getId(), bankEntity);
        return bankEntity;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws Exception {
        if (!this.memory.containsKey(bankEntity.getId())){
            throw new IllegalArgumentException();
        }

        Optional<BankEntity> first = this.memory.values()
                .stream()
                .filter(e -> e.getAcronym().equals(bankEntity.getAcronym()))
                .findFirst();

        if (first.isPresent() && !first.get().getId().equals(bankEntity.getId())){
            throw new IllegalArgumentException("Acronym already exists");
        }

        BankEntity mem = this.memory.get(bankEntity.getId());
        mem.setAcronym(bankEntity.getAcronym());
        mem.setName(bankEntity.getName());

        this.memory.put(mem.getId(), mem);
        return bankEntity;
    }

    @Override
    public boolean delete(String bankId) throws Exception {
       this.memory.remove(bankId);
       return true;
    }

    private String checkForMissingId(String id) {
        return (id == null ||id.isEmpty()) ? UUID.randomUUID().toString(): id;
    }

    // --------------------------------
    // Account Repo
    // --------------------------------
    @Override
    public AccountEntity create(String bank, AccountEntity entity) throws Exception {
        Optional<AccountEntity> first = this.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .filter(a -> a.getAcronym().equals(entity.getAcronym()))
                .findFirst();

        if (first.isPresent()){
            throw new IllegalArgumentException("Acronym already exists");
        }

        entity.setId(checkForMissingId(entity.getId()));

        if (!this.memory.containsKey(bank)){
            throw new IllegalArgumentException("Bank not exists");
        }

        BankEntity bankEntity = this.memory.get(bank);
        bankEntity.addAccount(entity);
        this.memory.put(bankEntity.getId(), bankEntity);
        return entity;
    }

    @Override
    public List<AccountEntity> getAccountsOfBank(String bank) throws Exception {
        return new ArrayList<>(this.memory.get(bank).getAccounts());
    }

    @Override
    public AccountEntity getAccountById(String Id) throws Exception {
        return null;
    }

    @Override
    public List<AccountEntity> getAccountsByAcronym(String acronym) {
        Optional<BankEntity> bank = this.memory.values().stream().filter(s -> s.getAcronym().equals(acronym)).findFirst();
        if (!bank.isPresent()){
            throw new IllegalArgumentException("Bank don't exists");
        }

        return new ArrayList<>(bank.get().getAccounts());
    }

    @Override
    public AccountEntity createByAcronym(String acronym, AccountEntity account) throws Exception {
        String id = this.getByAcronym(acronym).getId();
        return this.create(id, account);
    }

    @Override
    public boolean deleteAccountByAcronym(String acronym) {
        this.memory.values().forEach(bank -> {
            Optional<AccountEntity> first = bank.getAccounts()
                    .stream()
                    .filter(a -> a.getAcronym().equals(acronym))
                    .findFirst();

            first.ifPresent(entity -> {
                bank.removeAccount(entity.getId());
                this.memory.put(bank.getId(), bank);
            });
        });
        return true;
    }

    @Override
    public boolean deleteAccountById(String id) {
        this.memory.values().forEach(bank -> {
            Optional<AccountEntity> first = bank.getAccounts()
                    .stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst();

            first.ifPresent(entity -> bank.removeAccount(entity.getId()));
        });

        return false;
    }
}
