package de.janbraunsdorff.ase.layer.domain.contract;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.command.ContractCreateCommand;
import de.janbraunsdorff.ase.layer.domain.contract.data.Contract;
import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.TransactionWebDTO;

public class ContractIO implements ContractIOApplication{

    private final ContractRepository contractRepository;
    private final TransactionRepository transactionRepository;

    public ContractIO(ContractRepository contractRepository, TransactionRepository transactionRepository) {
        this.contractRepository = contractRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<ContractDTO> getContracts() {
        return contractRepository.getContracts().map(this::convertBankToDTO).collect(Collectors.toList());
    }

    @Override
    public ContractDTO createContract(ContractCreateCommand command) {
        var contract = new Contract(command.name(), command.expected(),  command.account(), command.start(), command.end(), command.expectedValue(), command.interval(), command.thirdParty());
        var contractReturn = contractRepository.save(contract);
        return convertBankToDTO(contractReturn);
    }


    private ContractDTO convertBankToDTO(Contract c) {
        Supplier<Stream<TransactionDTO>> transactionDTOs = () -> transactionRepository.getTransactionByIds(c.getTransactions()).map(TransactionDTO::new);
        var transactions = transactionDTOs.get().map(TransactionWebDTO::new).toList();
        var values = transactionDTOs.get().map(TransactionDTO::getValue).map(t -> t.getValue() / 100.0).toList();
        var total = new Value(transactionDTOs.get().map(TransactionDTO::getValue).reduce(Value::add).get().getValue());
        var average = new Value((int) (total.getValue() / (double) transactions.size()));
        return  new ContractDTO(
                c.getId(),
                c.getName(),
                c.getExpected(),
                c.getStart(),
                c.getEnd(),
                c.getTransactions().size(),
                average,
                c.getExpectedValue(),
                total,
                c.getInterval(),
                c.getAccountAcronym(),
                c.getThirdParty(),
                transactions,
                values);
    }


}
