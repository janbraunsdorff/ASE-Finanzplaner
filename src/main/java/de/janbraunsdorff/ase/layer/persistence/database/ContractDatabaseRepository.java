package de.janbraunsdorff.ase.layer.persistence.database;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import de.janbraunsdorff.ase.layer.domain.contract.ContractRepository;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;
import de.janbraunsdorff.ase.layer.persistence.database.entity.ContractDatabaseEntity;

public class ContractDatabaseRepository implements ContractRepository {

    private final ContractSpringRepository repo;
    private final AccountSpringRepository accountRepo;
    public ContractDatabaseRepository(ContractSpringRepository repo, AccountSpringRepository accountRepo) {
        this.repo = repo;
        this.accountRepo = accountRepo;
    }

    @Override
    public Stream<Contract> getContracts() {
        return StreamSupport.stream(this.repo.findAll().spliterator(), false)
                .map(ContractDatabaseEntity::toDomain);
    }

    @Override
    public Contract save(Contract contract) {
        var account = this.accountRepo.findByAcronym(contract.getAccountAcronym());
        return repo.save(new ContractDatabaseEntity(contract, account)).toDomain();
    }
}
