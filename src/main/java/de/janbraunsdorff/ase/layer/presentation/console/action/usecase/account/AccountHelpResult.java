package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.HelpPrinterInputFactory;

public class AccountHelpResult implements Result {
    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(80)
                .addHeadline("Hilfe (Account):")
                .addCommand("account all -a [Abkürzung Bank]", "zeigt alle Konten einer Bank an")
                .addCommand("account add -na [Name] -nr [Nummer] -ac [Abkürzung Account] -a [Abkürzung Bank]", "Fügt ein neues Konto hinzu")
                .addCommand("account delete -a [Abkürzung Account]", "Löscht account")
                .build();
    }
}
