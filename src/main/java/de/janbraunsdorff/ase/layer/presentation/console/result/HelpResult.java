package de.janbraunsdorff.ase.layer.presentation.console.result;


import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;

public class HelpResult implements Result {

    @Override
    public String print() {
        int commandSize = 50;
        return new OutputBuilder()
                .addText("Hilfe:")
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+ commandSize +"s%s", "bank", "alle optionen für Bank"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+ commandSize +"s%s", "account", "alle optionen für Account"))
                .addNewLine()
                .addCommand()
                .addText(String.format("%-"+ commandSize +"s%s", "option", "alle Options um die Anwenung zu Konfigurieren"))
                .addNewLine()
                .build();
    }
}
