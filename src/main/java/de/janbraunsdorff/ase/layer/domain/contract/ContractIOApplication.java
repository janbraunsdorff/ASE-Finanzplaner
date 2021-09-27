package de.janbraunsdorff.ase.layer.domain.contract;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;

public interface ContractIOApplication {
    List<ContractDTO> getContracts();
}
