package de.janbraunsdorff.ase.layer.presentation.console.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.printer.HelpOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class HelpResult implements Result {

    @Override
    public String print() {
        return new HelpOutputBuilder(50)
                .addHeadline("Hilfe:")
                .addCommand("bank", "alle Optionen für Bank")
                .addCommand("account", "alle Optionen für Account")
                .addCommand("option", "alle Optionen um die Anwendung zu Konfigurieren")
                .build();
    }
}
