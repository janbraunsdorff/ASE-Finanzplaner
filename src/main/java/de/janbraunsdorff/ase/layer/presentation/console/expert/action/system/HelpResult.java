package de.janbraunsdorff.ase.layer.presentation.console.expert.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.HelpPrinterInputFactory;

public class HelpResult implements Result {

    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(50)
                .addHeadline("Hilfe:")
                .addCommand("bank", "alle Optionen für Bank")
                .addCommand("account", "alle Optionen für Account")
                .addCommand("option", "alle Optionen um die Anwendung zu Konfigurieren")
                .build();
    }
}
