package de.janbraunsdorff.ase.layer.domain.contract;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;

public class ContractIO implements ContractIOApplication{

    private final ContractRepository contractRepository;

    public ContractIO(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public List<ContractDTO> getContractsByAccount(String parameter) {
        return null;
    }
}
