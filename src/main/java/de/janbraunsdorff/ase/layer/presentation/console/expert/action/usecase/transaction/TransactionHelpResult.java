package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.HelpPrinterInputFactory;

public class TransactionHelpResult implements Result {
    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(130)
                .addHeadline("Hilfe (Transactionen):")
                .addHeadline("")
                .addHeadline("Unix input:")
                .addHeadline("Hilfe (Transactionen):")
                .addCommand("ls {-n [Anzahl an Transactionen]}", "Zeigt die letzten 20 Transactionen an. Anzahl ist optional")
                .addCommand("touch -val [Betrag in Cent] -thp [Von / Nach] -dat [DD.MM.YY] -cat [Kategorie] {-con}", "Legt eine neue Transaction an. Datum, Kategorie und Vertrag sind optional")

                .addHeadline("")
                .addHeadline("Experteneingabe:")
                .addCommand("transaction all -a [Account Abkürzung] {-n [Anzahl an Transactionen]}", "Zeigt die letzten Transactionen an. Anzahl ist optional")
                .addCommand("transaction add -a [Account Abkürzung] -val [Betrag in Cent] -thp [Von / Nach] -dat [DD.MM.YY] -cat [Kategorie] {-con}", "Legt eine neue Transaction an. Datum, Kategorie und Vertrag sind optional")
                .build();
    }
}
