package de.janbraunsdorff.ase.layer.presentation.console.result.transaction;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Transaction;
import de.janbraunsdorff.ase.layer.presentation.console.printer.TableOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

import java.util.List;

public class TransactionResult implements TypedResult<Transaction> {
    private final List<Transaction> transactions;
    private final TableOutputBuilder builder;
    private final int lengthThirdParty;
    private final int lengthCategory;

    public TransactionResult(List<Transaction> transactions) {
        this.transactions = transactions;
        this.builder = new TableOutputBuilder();
        this.lengthThirdParty = getMax(v -> v.getThirdParty().length(), transactions);
        this.lengthCategory = getMax(v -> v.getCategory().length(), transactions);
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

    private void print(Transaction t){
        builder.addLine()
                .addEntry(t.getThirdParty())
                .addEntry(t.getDate().toString())
                .addEntry(t.getContract() ? "ja": "nein")
                .addEntry(t.getCategory())
                .addAmount(t.getValue())
                .addNewLine();
    }
}
