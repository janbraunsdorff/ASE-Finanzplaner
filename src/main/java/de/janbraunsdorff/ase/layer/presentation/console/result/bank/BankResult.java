package de.janbraunsdorff.ase.layer.presentation.console.result.bank;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.presentation.console.printer.TableOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

import java.util.List;

public class BankResult implements TypedResult<BankEntity> {
    private final List<BankEntity> result;
    private final TableOutputBuilder builder;

    private final int length;
    private final int lengthAcronym;

    public BankResult(List<BankEntity> result) {
        this.result = result;
        this.length = getMax(v1 -> v1.getName().length(), result);
        this.lengthAcronym = getMax(v -> v.getAcronym().length(), result);
        this.builder = new TableOutputBuilder();
    }

    @Override
    public String print() {
        builder
                .addTableHeader(37, "ID")
                .addTableHeader(length, "Name")
                .addTableHeader(lengthAcronym, "Abkürzung")
                .addTableHeader(10, "Account")
                .addTableHeader(10, "Guthaben")
                .finishFirstLine()
                .addHorizontalLine();

        this.result.forEach(this::print);

        return builder.build();
    }

    private void print(BankEntity r) {
        builder
                .addLine()
                .addEntry(r.getId())
                .addEntry(r.getName())
                .addEntry(r.getAcronym())
                .addEntry(String.valueOf(r.getAccounts().size()))
                .addAmount(r.getAmountOfAccountsInCent())
                .addNewLine();

    }

}
