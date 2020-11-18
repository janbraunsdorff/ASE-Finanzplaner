package de.janbraunsdorff.ase.layer.persistence.repositories.memory;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudBankRepository;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.IdAlreadyExitsException;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankMemoryRepository implements CrudBankRepository {
    private MemoryRepository repo;

    public BankMemoryRepository(MemoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Bank getBanks(String id) throws BankNotFoundExecption {
        if (!this.repo.memory.containsKey(id)) {
            throw new BankNotFoundExecption(id);
        }
        return this.repo.memory.get(id).convertToDomainEntity();
    }

    public Bank getBankByAcronym(String acronym) throws BankNotFoundExecption {
        Optional<BankMemoryEntity> first = this.repo.memory.values().stream().
                filter(s -> s.getAcronym().equals(acronym)).
                findFirst();

        if (!first.isPresent()) {
            throw new BankNotFoundExecption(acronym);
        }
        return first.get().convertToDomainEntity();
    }

    @Override
    public List<Bank> getBanks() {
        return this.repo.memory.values().stream().map(BankMemoryEntity::convertToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public void createBank(Bank bankEntity) throws AcronymAlreadyExistsException, IdAlreadyExitsException {
        if (this.repo.memory.containsKey(bankEntity.getAcronym())){
            throw new AcronymAlreadyExistsException(bankEntity.getAcronym());
        }


        Optional<BankMemoryEntity> first = this.repo.memory.values()
                .stream()
                .filter(e -> e.getAcronym().equals(bankEntity.getAcronym()))
                .findFirst();

        if (first.isPresent()) {
            throw new AcronymAlreadyExistsException(bankEntity.getAcronym());
        }

        this.repo.memory.put(bankEntity.getAcronym(), new BankMemoryEntity(bankEntity));
    }

    @Override
    public void deleteBankById(String bankId) {
        this.repo.memory.remove(bankId);
    }

    @Override
    public void deleteBankByAcronym(String acronym) {
        Optional<BankMemoryEntity> entity = this.repo.memory.values().stream()
                .filter(s -> s.getAcronym().equals(acronym))
                .findFirst();

        entity.ifPresent(bankEntity -> this.deleteBankById(bankEntity.getId()));
    }
}
