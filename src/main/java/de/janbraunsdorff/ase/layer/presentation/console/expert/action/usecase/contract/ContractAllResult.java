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
    private final int expectedLength;
    private final DateTimeFormatter dtFormatter;

    public ContractAllResult(List<ContractDTO> contractsByAccount) {
        this.contractsByAccount = contractsByAccount;

        this.nameLength = getMax(v1 -> v1.name().length(), contractsByAccount);
        this.startLength = getMax(v1 -> v1.start().toString().length(), contractsByAccount);
        this.expectedLength = getMax(v1 -> v1.expected().length(), contractsByAccount);

        this.builder = new TablePrinterInputFactory();
        this.dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public PrinterInput print() {
        this.builder
                .addHeadline("Contracts: ")
                .addTableHeader(nameLength, "Name")
                .addTableHeader(expectedLength, "expected")
                .addTableHeader(startLength, "Started")
                .addTableHeader(startLength, "Ended")
                .addTableHeader(10, "Expected")
                .addTableHeader(10, "Average")
                .addTableHeader(10, "Transactions")
                .addTableHeader(10, "Total Expense")
                .finishFirstLine()
                .addHorizontalLine();

        this.contractsByAccount.forEach(this::print);

        return builder.build();
    }


    private void print(ContractDTO dto) {
        builder
                .addLine()
                .addEntry(dto.name())
                .addEntry(dto.expected())
                .addEntry(this.dtFormatter.format(dto.start()))
                .addEntry(this.dtFormatter.format(dto.end()))
                .addAmount(dto.expectedAmount())
                .addAmount(dto.averageAmount())
                .addEntry(String.valueOf(dto.numberOfTransactions()))
                .addAmount(dto.totalExpense())
                .addNewLine();
    }

}
