package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;


import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.TypedResult;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.TablePrinterInputFactory;

import java.util.List;

public class AccountAllResult implements TypedResult<AccountDTO> {
    private final List<AccountDTO> result;
    private final TablePrinterInputFactory builder;
    private final int lengthName;
    private final int lengthNumber;

    public AccountAllResult(List<AccountDTO> result) {
        this.result = result;
        this.builder = new TablePrinterInputFactory();
        this.lengthName = getMax(v -> v.getName().length(), result);
        this.lengthNumber = getMax(v -> v.getAccountNumber().length(), result);
    }

    @Override
    public PrinterInput print() {
        this.builder
                //.addTableHeader(37, "ID")
                .addTableHeader(lengthName, "Name")
                .addTableHeader(lengthNumber, "Number")
                .addTableHeader(10, "Abkürzung")
                .addTableHeader(13, "Transactionen")
                .addTableHeader(10, "Guthaben")
                .finishFirstLine()
                .addHorizontalLine();

        this.result.forEach(this::print);
        return builder.build();
    }

    private void print(AccountDTO r) {
        builder
                .addLine()
                //.addEntry(r.getId())
                .addEntry(r.getName())
                .addEntry(r.getAccountNumber())
                .addEntry(r.getAcronym())
                .addEntry(r.getNumberOfTransaction().toString())
                .addAmount(r.getValue())
                .addNewLine();
    }

}