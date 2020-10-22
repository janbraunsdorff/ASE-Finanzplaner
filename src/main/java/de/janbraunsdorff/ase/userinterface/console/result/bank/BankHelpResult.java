package de.janbraunsdorff.ase.userinterface.console.result.bank;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;
import de.janbraunsdorff.ase.userinterface.console.result.Result;


public class BankHelpResult implements Result {
    @Override
    public String print() {
        final int commandSize = 50;

        return new OutputBuilder()
                .addText("Hilfe (Bank):")
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "bank all", "zeigt alle Banken an"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "bank add -n [name] -a [abkürzung]", "legt eine neue Bank mit einem Name und einer Abkürzung an"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "bank update -id [ID] -n [name] -a [abkürzung]", "aktualiserit den Namen einer Bank und die Abkürzung"))
                .addNewLine()
                .build();
    }
}
