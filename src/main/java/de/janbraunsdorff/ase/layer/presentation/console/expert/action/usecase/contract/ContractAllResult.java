package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract;

import java.time.format.DateTimeFormatter;
import java.util.List;

import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.TypedResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.TablePrinterInputFactory;

public class ContractAllResult implements TypedResult<ContractDTO> {
    private final List<ContractDTO> contractsByAccount;
    private final TablePrinterInputFactory builder;

    private final int nameLength;
    private final int startLength;
    private final DateTimeFormatter dtFormatter;

    public ContractAllResult(List<ContractDTO> contractsByAccount) {
        this.contractsByAccount = contractsByAccount;

        this.nameLength = getMax(v1 -> v1.name().length(), contractsByAccount);
        this.startLength = getMax(v1 -> v1.start().toString().length(), contractsByAccount);

        this.builder = new TablePrinterInputFactory();
        this.dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public PrinterInput print() {
        this.builder
                .addHeadline("Contracts: ")
                .addTableHeader(nameLength, "Name")
                .addTableHeader(startLength, "Started")
                .addTableHeader(10, "Expected")
                .addTableHeader(10, "Average")
                .addTableHeader(10, "#")
                .finishFirstLine()
                .addHorizontalLine();

        this.contractsByAccount.forEach(this::print);

        return builder.build();
    }


    private void print(ContractDTO dto) {
        builder
                .addLine()
                .addEntry(dto.name())
                .addEntry(this.dtFormatter.format(dto.start()))
                .addAmount(dto.expectedAmount())
                .addAmount(dto.averageAmount())
                .addEntry(String.valueOf(dto.numberOfTransactions()))
                .addNewLine();
    }

}