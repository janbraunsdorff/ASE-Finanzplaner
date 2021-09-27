package de.janbraunsdorff.ase.layer.domain.contract;

import java.util.List;
import java.util.stream.Collectors;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.command.ContractCreateCommand;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;
import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;

public class ContractIO implements ContractIOApplication{

    private final ContractRepository contractRepository;

    public ContractIO(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public List<ContractDTO> getContracts() {
        return contractRepository.getContracts().map(this::convertBankToDTO).collect(Collectors.toList());
    }

    @Override
    public ContractDTO createContract(ContractCreateCommand command) {
        var contract = new Contract(command.name(), command.account(), command.start(), command.end(), command.expectedValue());
        var contractReturn = contractRepository.save(contract);
        return convertBankToDTO(contractReturn);
    }


    private ContractDTO convertBankToDTO(Contract c) {
        return  new ContractDTO(c.getName(), c.getStart(), c.getEnd(), c.getTransactions().size(), new Value(0), c.getExpectedValue(), new Value(0));
    }


}
