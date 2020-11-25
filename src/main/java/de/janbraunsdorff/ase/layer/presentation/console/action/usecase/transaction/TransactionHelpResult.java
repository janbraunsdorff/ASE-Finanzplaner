package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.HelpPrinterInputFactory;

public class TransactionHelpResult implements Result {
    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(130)
                .addHeadline("Hilfe (Transactionen):")
                .addCommand("transaction all -a [Account Abkürzung] {-n [Anzahl an Transactionen]}", "Zeigt die letzten Transactionen an. Anzahl ist optional")
                .addCommand("transaction add -a [Account Abkürzung] -val [Betrag in Cent] -thp [Von / Nach] {-dat [DD.MM.YY]} {-cat [Kategorie]} {-con}", "Legt eine neue Transaction an. Datum, Kategorie und Vertrag sind optional")
                .addCommand("---Defaults---", "---values---")
                .addCommand("-l  , length", "20")
                .addCommand("-dat, date", "Aktuelles Datum")
                .addCommand("-cat, category", "")
                .addCommand("-con, contract", "nicht gesetzt == false")
                .build();
    }
}
