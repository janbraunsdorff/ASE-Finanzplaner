package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.TypedResult;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.TablePrinterInputFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionAllResult implements TypedResult<TransactionDTO> {
    private final List<TransactionDTO> transactions;
    private boolean withId;
    private final TablePrinterInputFactory builder;
    private final int lengthThirdParty;
    private final int lengthCategory;
    private final DateTimeFormatter dtFormatter;
    private int value;

    private LocalDate date = LocalDate.now();

    public TransactionAllResult(List<TransactionDTO> transactions, boolean withId) {
        this.transactions = transactions;
        this.withId = withId;
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
                .addTableHeader(this.withId, 36, "Unique Ident")
                .finishFirstLine()
                .addHorizontalLine();
        this.transactions.forEach(this::print);

        return builder.build();
    }

    private void print(TransactionDTO t) {
        if (this.date.getMonthValue() != t.getDate().getMonthValue() || this.date.getYear() != t.getDate().getYear()) {
            this.builder.addHorizontalLine();
            this.builder.addLine()
                    .addEntry("")
                    .addEntry("")
                    .addEntry("")
                    .addEntry("")
                    .addAmount(value)
                    .addEntry(this.withId, "")
                    .addNewLine();

            this.date = t.getDate();
            this.builder.addNewLine();
            this.value = 0;
        }

        this.value += t.getValue();


        this.builder.addLine()
                .addEntry(t.getThirdParty())
                .addEntry(this.dtFormatter.format(t.getDate()))
                .addEntry(t.getContract() ? "   x   " : "")
                .addEntry(t.getCategory())
                .addAmount(t.getValue())
                .addEntry(this.withId, t.getId())
                .addNewLine();
    }
}
