package de.janbraunsdorff.ase.layer.persistence.database;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BankDatabaseRepository implements BankRepository {

    private final BankSpringRepository repo;

    public BankDatabaseRepository(BankSpringRepository repo){

        this.repo = repo;
    }

    @Override
    public List<Bank> getBank() {
        return StreamSupport.stream(this.repo.findAll().spliterator(), false)
                .map(BankDatabaseEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Bank getBankByAcronym(String acronym) throws BankNotFoundException {
        return this.repo.findByAcronym(acronym).toDomain();
    }

    @Override
    public void createBank(Bank bankEntity) throws AcronymAlreadyExistsException {
        BankDatabaseEntity entity = new BankDatabaseEntity(bankEntity.getId(), bankEntity.getName(), bankEntity.getAcronym(), bankEntity.getType().getName());
        this.repo.save(entity);
    }

    @Override
    public void deleteBankByAcronym(String acronym) {
        this.repo.deleteByAcronym(acronym);
    }
}
