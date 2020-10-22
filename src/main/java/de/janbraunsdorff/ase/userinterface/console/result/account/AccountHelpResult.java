package de.janbraunsdorff.ase.userinterface.console.result.account;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;
import de.janbraunsdorff.ase.userinterface.console.result.Result;

public class AccountHelpResult implements Result {
    @Override
    public String print() {
        final int commandSize = 50;

        return new OutputBuilder()
                .addText("Hilfe (Account):")
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "account all -i [ID] ", "zeigt alle Konten einer Bank an"))
                .addNewLine()
                .build();
    }
}
