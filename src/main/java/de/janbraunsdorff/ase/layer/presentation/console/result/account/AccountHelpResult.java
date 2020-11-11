package de.janbraunsdorff.ase.layer.presentation.console.result.account;

import de.janbraunsdorff.ase.layer.presentation.console.printer.HelpOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class AccountHelpResult implements Result {
    @Override
    public String print() {
        final int commandSize = 80;

        return new HelpOutputBuilder(80)
                .addHeadline("Hilfe (Account):")
                .addCommand("account all -i [ID Bank]", "zeigt alle Konten einer Bank an")
                .addCommand("account all -a [Abkürzung Bank]", "zeigt alle Konten einer Bank an")
                .addCommand("account add -na [Name] -nr [Nummer] -ac [Abkürzung Account] -i [ID Bank]", "Fügt ein neues Konto hinzu")
                .addCommand("account add -na [Name] -nr [Nummer] -ac [Abkürzung Account] -a [Abkürzung Bank]", "Fügt ein neues Konto hinzu")
                .addCommand("account delete -a [Abkürzung Account]", "Löscht account")
                .addCommand("account delete -i [ID Account]", "Löscht account")
                .build();
    }
}
