package de.janbraunsdorff.ase.layer.domain.contract;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;

public interface ContractRepository {
    List<Contract> getContracts();
}
