package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.TypedResult;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.TablePrinterInputFactory;

import java.util.List;

public class BankAllResult implements TypedResult<BankDTO> {
    private final List<BankDTO> result;
    private final TablePrinterInputFactory builder;

    private final int length;
    private final int lengthAcronym;

    public BankAllResult(List<BankDTO> result) {
        this.result = result;
        this.length = getMax(v1 -> v1.getName().length(), result);
        this.lengthAcronym = getMax(v -> v.getAcronym().length(), result);
        this.builder = new TablePrinterInputFactory();
    }

    @Override
    public PrinterInput print() {
        builder
                .addTableHeader(length, "Name")
                .addTableHeader(lengthAcronym, "Abkürzung")
                .addTableHeader(10, "Account")
                .addTableHeader(10, "Guthaben")
                .finishFirstLine()
                .addHorizontalLine();

        this.result.forEach(this::print);

        return builder.build();
    }

    private void print(BankDTO r) {
        builder
                .addLine()
                .addEntry(r.getName())
                .addEntry(r.getAcronym())
                .addEntry(r.getNumberOfAccount().toString())
                .addAmount(r.getValue())
                .addNewLine();
    }

}
