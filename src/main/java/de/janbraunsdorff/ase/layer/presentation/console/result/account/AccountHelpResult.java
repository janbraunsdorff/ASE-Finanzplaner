package de.janbraunsdorff.ase.layer.presentation.console.result.account;

import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class AccountHelpResult implements Result {
    @Override
    public String print() {
        final int commandSize = 80;

        return new OutputBuilder()
                .addText("Hilfe (Account):")
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "account all -i [ID Bank] ", "zeigt alle Konten einer Bank an"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "account all -a [Abkürzung Bank] ", "zeigt alle Konten einer Bank an"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "account add -na [Name] -nr [Nummer] -ac [Abkürzung Account] -i [ID Bank] ", "Fügt ein neues Konto hinzu"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+commandSize+"s\t%s", "account add -na [Name] -nr [Nummer] -ac [Abkürzung Account] -a [Abkürzung Bank] ", "Fügt ein neues Konto hinzu"))
                .addNewLine()
                .build();
    }
}
