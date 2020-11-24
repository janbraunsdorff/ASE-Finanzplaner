package de.janbraunsdorff.ase.layer.presentation.console.result.account;


import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.presentation.console.printer.TableOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

import java.util.List;

public class AccountResult implements TypedResult<AccountDTO> {
    private final List<AccountDTO> result;
    private final TableOutputBuilder builder;
    private final int lengthName;
    private final int lengthNumber;

    public AccountResult(List<AccountDTO> result) {
        this.result = result;
        this.builder = new TableOutputBuilder();
        this.lengthName = getMax(v -> v.getName().length(), result);
        this.lengthNumber = getMax(v -> v.getAccountNumber().length(), result);
    }

    @Override
    public String print() {
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
