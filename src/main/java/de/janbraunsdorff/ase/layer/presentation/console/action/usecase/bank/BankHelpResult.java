package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;


import de.janbraunsdorff.ase.layer.presentation.console.printer.HelpOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class BankHelpResult implements Result {
    @Override
    public String print() {
        return new HelpOutputBuilder(50)
                .addHeadline("Hilfe (Bank):")
                .addCommand("bank all", "zeigt alle Banken an")
                .addCommand("bank add -n [name] -a [abkürzung]", "legt eine neue Bank mit einem Name und einer Abkürzung an")
                .addCommand("bank delete -a [abkürzung]", "Löscht Bank basierend auf der Abkürzung")
                .build();
    }
}
