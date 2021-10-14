package de.janbraunsdorff.ase.layer.presentation.web.controller.contract;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.ContractIOApplication;
import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;
import de.janbraunsdorff.ase.layer.presentation.web.controller.contract.data.AllContractRequest;
import de.janbraunsdorff.ase.layer.presentation.web.controller.contract.data.AllContractResponse;
import de.janbraunsdorff.ase.layer.presentation.web.controller.contract.data.ContractWebDTO;
import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.TransactionWebDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/contract")
public class ContractController {
    public final ContractIOApplication io;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public ContractController(ContractIOApplication io) {
        this.io = io;
    }

    @PostMapping("/all")
    public AllContractResponse getContracts(@RequestBody AllContractRequest request) {
        System.out.println(request.withInactive);
        var contracts = this.io.getContracts();
        var income = convertContracts(contracts, true, request.isWithInactive());
        var totalIncome = totalAmount(contracts, true);
        var outcome = convertContracts(contracts, false, request.isWithInactive());
        var totalOutcome = totalAmount(contracts, false);

        return new AllContractResponse(income.toList(), totalIncome, outcome.toList(), totalOutcome);
    }

    private String totalAmount(List<ContractDTO> contractDTOStream, boolean postive) {
        return contractDTOStream.stream()
                .filter(c -> c.expectedAmount().isPositive() == postive)
                .filter(c -> c.end().isAfter(LocalDate.now()))
                .map(ContractDTO::nomalize)
                .map(ContractDTO::expectedAmount)
                .reduce(Value::add)
                .get()
                .getFormatted();
    }


    private Stream<ContractWebDTO> convertContracts(List<ContractDTO> contractDTOStream, boolean postive, boolean withInActive) {
        return contractDTOStream
                .stream()
                .filter(c -> c.expectedAmount().isPositive() == postive)
                .map(this::convertToDTO)
                .filter(c -> {
                    if (withInActive){
                        return true;
                    }
                    return c.isActive;
                });
    }



    private ContractWebDTO convertToDTO(ContractDTO contract) {
        return new ContractWebDTO(
                contract.id(),
                contract.name(),
                contract.expected(),
                contract.expectedAmount().getFormatted(),
                contract.expectedAmount().isPositive(),
                contract.end().isAfter(LocalDate.now()),
                contract.account(),
                contract.start().format(dtf),
                contract.end().format(dtf),
                contract.interval().name(),
                contract.averageAmount().getFormatted(),
                contract.transactions(),
                contract.totalExpense().getFormatted(),
                contract.thirdParty(),
                contract.transactions().stream().map(TransactionWebDTO::getDate).toList(),
                contract.getValues());
    }
}
