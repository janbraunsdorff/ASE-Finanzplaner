package de.janbraunsdorff.ase.layer.domain.contract;

import java.util.List;
import java.util.stream.Stream;

import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;

public interface ContractRepository {
    Stream<Contract> getContracts();
    Contract save(Contract contract);
}
