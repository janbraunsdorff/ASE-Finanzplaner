package de.janbraunsdorff.ase.layer.presentation.console.result.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.printer.TableOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionResult implements TypedResult<TransactionDTO> {
    private final List<TransactionDTO> transactions;
    private final TableOutputBuilder builder;
    private final int lengthThirdParty;
    private final int lengthCategory;
    private final DateTimeFormatter dtFormatter;

    public TransactionResult(List<TransactionDTO> transactions) {
        this.transactions = transactions;
        this.builder = new TableOutputBuilder();
        this.lengthThirdParty = getMax(v -> v.getThirdParty().length(), transactions);
        this.lengthCategory = getMax(v -> v.getCategory().length(), transactions);
        this.dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public String print() {
        this.builder.
                addTableHeader(lengthThirdParty, "Von/Nach")
                .addTableHeader(10, "Datum")
                .addTableHeader(7, "Vertrag")
                .addTableHeader(lengthCategory, "Kategorie")
                .addTableHeader(7, "Betrag")
                .finishFirstLine()
                .addHorizontalLine();
        this.transactions.forEach(this::print);

        return builder.build();
    }

    private void print(TransactionDTO t) {
        builder.addLine()
                .addEntry(t.getThirdParty())
                .addEntry(this.dtFormatter.format(t.getDate()))
                .addEntry(t.getContract() ? "ja" : "nein")
                .addEntry(t.getCategory())
                .addAmount(t.getValue())
                .addNewLine();
    }
}
