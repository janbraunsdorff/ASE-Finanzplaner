package de.janbraunsdorff.ase.tech.repositories.memory;

import de.janbraunsdorff.ase.tech.repositories.CrudAccountRepository;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;
import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;

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
        return this.memory.values().stream().
                filter(s -> s.getAcronym().equals(acronym)).
                findFirst().
                orElseGet(BankEntity::new);
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

        bankEntity.setId(checkForMissingId(bankEntity.getId()));
        this.memory.put(bankEntity.getId(), bankEntity);
        return bankEntity;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws Exception {
        if (!this.memory.containsKey(bankEntity.getId())){
            throw new IllegalArgumentException();
        }

        Optional<BankEntity> first = this.memory.values().stream().filter(e -> e.getAcronym().equals(bankEntity.getAcronym())).findFirst();
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
        entity.setId(checkForMissingId(entity.getId()));
        BankEntity bankEntity = this.memory.get(bank);
        bankEntity.getAccounts().add(entity);
        this.memory.put(bank, bankEntity);
        return entity;
    }

    @Override
    public List<AccountEntity> getAccountsOfBank(String bank) throws Exception {
        return this.memory.get(bank).getAccounts();
    }

    @Override
    public AccountEntity getAccountById(String Id) throws Exception {
        return null;
    }
}
