package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;
import de.janbraunsdorff.ase.tech.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.userinterface.console.result.TypedResult;

import java.util.List;

public class BankResult implements TypedResult<BankEntity> {
    private final List<BankEntity> result;
    private final OutputBuilder builder;

    private final int length;
    private final int lengthAcronym;

    public BankResult(List<BankEntity> result) {
        this.result = result;
        this.length = getMax(v1 -> v1.getName().length(), result);
        this.lengthAcronym = getMax(v -> v.getAcronym().length(), result);
        this.builder = new OutputBuilder();
    }

    @Override
    public String print() {
        builder.addTableVerticalDivider()
                .addText(String.format("%-37s", "ID"))
                .addTableVerticalDivider()
                .addText(String.format("%-" + length + "s", "Name"))
                .addTableVerticalDivider()
                .addText(String.format("%-" + lengthAcronym + "s", "Abkürzung"))
                .addTableVerticalDivider()
                .addText(String.format("%-10s", "Accounts"))
                .addTableVerticalDivider()
                .addNewLine()
                .addTableHorizontal(37, length, lengthAcronym, 10)
                .addNewLine();

        this.result.forEach(this::print);

        return builder.build();
    }

    private void print(BankEntity r) {
        builder.addTableVerticalDivider()
                .addText(String.format("%-37s", r.getId()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + length + "s", r.getName()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + lengthAcronym + "s", r.getAcronym()))
                .addTableVerticalDivider()
                .addText(String.format("%-10s", r.getAccounts().size()))
                .addTableVerticalDivider()
                .addNewLine();

    }

}
