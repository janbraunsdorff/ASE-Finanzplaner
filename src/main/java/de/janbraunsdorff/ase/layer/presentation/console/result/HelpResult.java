package de.janbraunsdorff.ase.layer.presentation.console.result;


import de.janbraunsdorff.ase.layer.presentation.console.printer.HelpOutputBuilder;

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
