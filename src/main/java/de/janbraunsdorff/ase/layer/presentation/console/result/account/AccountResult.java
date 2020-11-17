package de.janbraunsdorff.ase.layer.presentation.console.result.account;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.presentation.console.printer.TableOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

import java.util.List;

public class AccountResult implements TypedResult<Account> {
    private final List<Account> result;
    private final TableOutputBuilder builder;
    private final int lengthName;
    private final int lengthNumber;

    public AccountResult(List<Account> result) {
        this.result = result;
        this.builder = new TableOutputBuilder();
        this.lengthName = getMax(v -> v.getName().length(), result);
        this.lengthNumber = getMax(v -> v.getNumber().length(), result);
    }

    @Override
    public String print() {
        this.builder
                .addTableHeader(37, "ID")
                .addTableHeader(lengthName, "Name")
                .addTableHeader(lengthNumber, "Number")
                .addTableHeader(10, "Abkürzung")
                .addTableHeader(10, "Guthaben")
                .finishFirstLine()
                .addHorizontalLine();

        this.result.forEach(this::print);
        return builder.build();
    }

    private void print(Account r) {
        builder
                .addLine()
                .addEntry(r.getId())
                .addEntry(r.getName())
                .addEntry(r.getNumber())
                .addEntry(r.getAcronym())
                .addAmount(r.getAmountOfAccountInCent())
                .addNewLine();
    }

}
