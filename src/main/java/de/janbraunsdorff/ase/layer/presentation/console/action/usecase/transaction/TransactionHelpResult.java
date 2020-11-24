package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.printer.HelpOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class TransactionHelpResult implements Result {
    @Override
    public String print() {
        return new HelpOutputBuilder(130)
                .addHeadline("Hilfe (Transactionen):")
                .addCommand("transaction all -a [Account Abkürzung] {-l [Anzahl an Transactionen]}", "Zeigt die letzten Transactionen an. Anzahl ist optional")
                .addCommand("transaction add -a [Account Abkürzung] -val [Betrag in Cent] -thp [Von / Nach] {-dat [DD.MM.YY]} {-cat [Kategorie]} {-con}", "Legt eine neue Transaction an. Datum, Kategorie und Vertrag sind optional")
                .addCommand("---Defaults---", "---values---")
                .addCommand("-l  , length", "20")
                .addCommand("-dat, date", "Aktuelles Datum")
                .addCommand("-cat, category", "")
                .addCommand("-con, contract", "nicht gesetzt == false")
                .build();
    }
}
