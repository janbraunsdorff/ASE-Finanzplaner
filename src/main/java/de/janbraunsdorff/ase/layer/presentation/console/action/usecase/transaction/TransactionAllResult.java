package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.TypedResult;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.TablePrinterInputFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionAllResult implements TypedResult<TransactionDTO> {
    private final List<TransactionDTO> transactions;
    private final TablePrinterInputFactory builder;
    private final int lengthThirdParty;
    private final int lengthCategory;
    private final DateTimeFormatter dtFormatter;


    private int month = 0;

    public TransactionAllResult(List<TransactionDTO> transactions) {
        this.transactions = transactions;
        this.builder = new TablePrinterInputFactory();
        this.lengthThirdParty = getMax(v -> v.getThirdParty().length(), transactions);
        this.lengthCategory = getMax(v -> v.getCategory().length(), transactions);
        this.dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public PrinterInput print() {
        this.builder.
                addTableHeader(lengthThirdParty, "Von/Nach")
                .addTableHeader(10, "Datum")
                .addTableHeader(7, "Vertrag")
                .addTableHeader(lengthCategory, "Kategorie")
                .addTableHeader(15, "Betrag")
                .finishFirstLine()
                .addHorizontalLine();
        this.transactions.forEach(this::print);

        return builder.build();
    }

    private void print(TransactionDTO t) {
        if (month != 0 && month != t.getDate().getMonthValue()){
            this.month = t.getDate().getMonthValue();
            builder.addNewLine();
        }
        this.month = t.getDate().getMonthValue();

        builder.addLine()
                .addEntry(t.getThirdParty())
                .addEntry(this.dtFormatter.format(t.getDate()))
                .addEntry(t.getContract() ? "ja" : "nein")
                .addEntry(t.getCategory())
                .addAmount(t.getValue())
                .addNewLine();
    }
}
