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
                .addCommand("ls", "Zeigt die letzten 20 Transactionen an.")
                .addCommand("ls -n [Anzahl an Transactionen]", "Zeigt die letzten n Transactionen an")
                .addCommand("ls -f ", "Zeigt die letzten 20 Transactionen mit id an")
                .addCommand("touch -val [Betrag in Cent] -thp [Von / Nach] -dat [DD.MM.YY] -cat [Kategorie] {-con}", "Legt eine neue Transaction an. Datum, Kategorie und Vertrag sind optional")
                .addCommand("rm [id]", "Löscht Transaction mit der ID")

                .addHeadline("")
                .addHeadline("Experteneingabe:")
                .addCommand("transaction all -a [Account Abkürzung] {-n [Anzahl an Transactionen]}", "Zeigt die letzten Transactionen an. Anzahl ist optional")
                .addCommand("transaction add -a [Account Abkürzung] -val [Betrag in Cent] -thp [Von / Nach] -dat [DD.MM.YY] -cat [Kategorie] {-con}", "Legt eine neue Transaction an. Datum, Kategorie und Vertrag sind optional")
                .addCommand("transaction delete -id [ID]", "Löscht Transaction mit der ID")
                .build();
    }
}
