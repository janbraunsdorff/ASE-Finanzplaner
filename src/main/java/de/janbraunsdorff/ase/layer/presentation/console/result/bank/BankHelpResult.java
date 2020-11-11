package de.janbraunsdorff.ase.layer.presentation.console.result.bank;


import de.janbraunsdorff.ase.layer.presentation.console.printer.HelpOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class BankHelpResult implements Result {
    @Override
    public String print() {
        return new HelpOutputBuilder(50)
                .addHeadline("Hilfe (Bank):")
                .addCommand("bank all", "zeigt alle Banken an")
                .addCommand("bank add -n [name] -a [abkürzung]", "legt eine neue Bank mit einem Name und einer Abkürzung an")
                .addCommand("bank update -i [ID] -n [name] -a [abkürzung]", "aktualiserit den Namen einer Bank und die Abkürzung")
                .addCommand("bank delete -i [ID]", "Löscht Bank basierend auf der Id")
                .addCommand("bank delete -a [abkürzung]", "Löscht Bank basierend auf der Abkürzung")
                .build();
    }
}
