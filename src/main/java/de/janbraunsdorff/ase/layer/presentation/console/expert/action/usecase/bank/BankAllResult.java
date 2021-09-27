package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.TypedResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.TablePrinterInputFactory;

public class BankAllResult implements TypedResult<BankDTO> {
    private final List<BankDTO> result;
    private final TablePrinterInputFactory builder;

    private final int length;
    private final int lengthAcronym;
    private final int typeLength;

    public BankAllResult(List<BankDTO> result) {
        this.result = result;
        this.length = getMax(v1 -> v1.name().length(), result);
        this.lengthAcronym = getMax(v -> v.acronym().length(), result);
        this.typeLength = getMax(v -> v.type().getName().length(), result);
        this.builder = new TablePrinterInputFactory();
    }

    @Override
    public PrinterInput print() {
        builder
                .addHeadline("Banken:")
                .addTableHeader(length, "Name")
                .addTableHeader(lengthAcronym, "Abkürzung")
                .addTableHeader(typeLength, "Typ")
                .addTableHeader(10, "Account")
                .addTableHeader(15, "Guthaben")
                .finishFirstLine()
                .addHorizontalLine();


        this.result.forEach(this::print);

        return builder.build();
    }

    private void print(BankDTO r) {
        builder
                .addLine()
                .addEntry(r.name())
                .addEntry(r.acronym())
                .addEntry(r.type().getName())
                .addEntry(r.numberOfAccount().toString())
                .addAmount(r.value())
                .addNewLine();
    }

}
