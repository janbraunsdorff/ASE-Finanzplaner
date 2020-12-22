package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.HelpPrinterInputFactory;

public class AccountHelpResult implements Result {
    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(80)
                .addHeadline("Hilfe (Account):")
                .addHeadline("")
                .addHeadline("Unix input:")
                .addCommand("ls", "Anzeigen aller Accounts im Path")
                .addCommand("cat [Abkürzung]", "Transaction eines Accounts anzeigen")
                .addCommand("cd [Abkürzung]", "Wechseln in Kontext der Transactionen vom Account")
                .addCommand("cd ..", "Wechseln in Kontext der Banken vom Account")
                .addCommand("touch -na [Name] -ac [Abkürzung] -nr [Nummer]", "Account anlegen")
                .addCommand("rm -a [Abkürzung]", "Account Löschen")

                .addHeadline("")
                .addHeadline("Experteneingabe:")
                .addCommand("account all -a [Abkürzung Bank]", "zeigt alle Konten einer Bank an")
                .addCommand("account add -na [Name] -nr [Nummer] -ac [Abkürzung Account] -a [Abkürzung Bank]", "Fügt ein neues Konto hinzu")
                .addCommand("account delete -a [Abkürzung Account]", "Löscht account")
                .build();
    }
}
