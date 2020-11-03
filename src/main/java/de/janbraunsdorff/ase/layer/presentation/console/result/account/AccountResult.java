package de.janbraunsdorff.ase.layer.presentation.console.result.account;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

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
                .addNewLine();
    }

}
