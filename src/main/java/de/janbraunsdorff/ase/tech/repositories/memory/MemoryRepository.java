package de.janbraunsdorff.ase.tech.repositories.memory;

import de.janbraunsdorff.ase.tech.repositories.BankEntity;
import de.janbraunsdorff.ase.tech.repositories.CrudBankRepository;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryRepository implements CrudBankRepository {

    private Map<String, BankEntity> memory = new HashMap<>();

    @Override
    public BankEntity get(String Id) throws Exception {
        return memory.get(Id);
    }

    @Override
    public List<BankEntity> get() throws Exception {
        return new ArrayList<>(memory.values());
    }

    @Override
    public BankEntity create(BankEntity bankEntity) throws Exception {
        if (memory.containsKey(bankEntity.getId())){
            throw new IllegalArgumentException("Id already exists");
        }

        Optional<BankEntity> first = memory.values().stream().filter(e -> e.getAcronym().equals(bankEntity.getAcronym())).findFirst();
        if (first.isPresent()){
            throw new IllegalArgumentException("Acronym already exists");
        }

        if (checkForMissingId(bankEntity)){
            bankEntity.setId(UUID.randomUUID().toString());
        }
        memory.put(bankEntity.getId(), bankEntity);
        return bankEntity;
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws Exception {
        if (!memory.containsKey(bankEntity.getId())){
            throw new IllegalArgumentException();
        }

        Optional<BankEntity> first = memory.values().stream().filter(e -> e.getAcronym().equals(bankEntity.getAcronym())).findFirst();
        if (first.isPresent() && !first.get().getId().equals(bankEntity.getId())){
            throw new IllegalArgumentException("Acronym already exists");
        }

        memory.put(bankEntity.getId(), bankEntity);
        return bankEntity;
    }

    @Override
    public boolean delete(String bankId) throws Exception {
       memory.remove(bankId);
       return true;
    }

    private boolean checkForMissingId(BankEntity bankEntity) {
        return bankEntity.getId() == null || bankEntity.getId().isEmpty();
    }

}
