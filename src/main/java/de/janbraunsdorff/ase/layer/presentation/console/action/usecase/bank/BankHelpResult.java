package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;


import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.HelpPrinterInputFactory;

public class BankHelpResult implements Result {
    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(35)
                .addHeadline("Hilfe (Bank)...")
                .addHeadline("")
                .addHeadline("Unix input:")
                .addCommand("ls", "Anzeigen aller Banken")
                .addCommand("cat [Abkürzung Bank]", "Accounts einer Bank anzeigen")
                .addCommand("cd [Abkürzung Bank]", "Wechseln in Kontext der Accounts einer Bank")
                .addCommand("touch -n [name] -a [abkürzung]", "Bank anlegen")
                .addCommand("rm -a [abkürzung]", "Bank Löschen")


                .addHeadline("")
                .addHeadline("Experteneingabe:")
                .addCommand("bank all", "zeigt alle Banken an")
                .addCommand("bank add -n [name] -a [abkürzung]", "legt eine neue Bank mit einem Name und einer Abkürzung an")
                .addCommand("bank delete -a [abkürzung]", "Löscht Bank basierend auf der Abkürzung")
                .build();
    }
}
