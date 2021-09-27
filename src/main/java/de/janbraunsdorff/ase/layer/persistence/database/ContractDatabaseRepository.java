package de.janbraunsdorff.ase.layer.persistence.database;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import de.janbraunsdorff.ase.layer.domain.contract.ContractRepository;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;

public class ContractDatabaseRepository implements ContractRepository {

    private final ContractSpringRepository repo;
    public ContractDatabaseRepository(ContractSpringRepository repo) {
        this.repo = repo;
    }

    @Override
    public Stream<Contract> getContracts() {
        return StreamSupport.stream(this.repo.findAll().spliterator(), false)
                .map(ContractDatabaseEntity::toDomain);
    }

    @Override
    public Contract save(Contract contract) {
        return repo.save(new ContractDatabaseEntity(contract)).toDomain();
    }
}
