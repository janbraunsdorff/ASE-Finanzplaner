package de.janbraunsdorff.ase.userinterface.console.result.account;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;
import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.userinterface.console.result.TypedResult;

import java.util.List;

public class AccountResult implements TypedResult<AccountEntity> {
    private final List<AccountEntity> result;
    private final OutputBuilder builder;
    private final int lengthName;
    private final int lengthNumber;

    public AccountResult(List<AccountEntity> result) {
        this.result = result;
        this.builder = new OutputBuilder();
        this.lengthName = getMax(v -> v.getName().length(), result);
        this.lengthNumber = getMax(v -> v.getNumber().length(), result);
    }

    @Override
    public String print() {
        this.builder.
            addTableVerticalDivider()
            .addText(String.format("%-37s", "ID"))
            .addTableVerticalDivider()
            .addText(String.format("%-" + lengthName + "s", "Name"))
            .addTableVerticalDivider()
            .addText(String.format("%-" + lengthNumber + "s", "Nummer"))
            .addTableVerticalDivider()
            .addText(String.format("%-" + 10 + "s", "Abkürzung"))
            .addTableVerticalDivider()
            .addText(String.format("%-" + 15 + "s", "Transactionen"))
            .addTableVerticalDivider()
            .addNewLine()
            .addTableHorizontal(37, lengthName, lengthNumber, 10,  15)
            .addNewLine();

        this.result.forEach(this::print);
        return builder.build();
    }

    private void print(AccountEntity r) {
        builder.addTableVerticalDivider()
                .addText(String.format("%-37s", r.getId()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + lengthName + "s", r.getName()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + lengthNumber + "s", r.getNumber()))
                .addTableVerticalDivider()
                .addText(String.format("%-" + 10 + "s", r.getAcronym()))
                .addTableVerticalDivider()
                .addText(String.format("%-15s", r.getTransactions().size()))
                .addTableVerticalDivider()
                .addNewLine();
    }

}
