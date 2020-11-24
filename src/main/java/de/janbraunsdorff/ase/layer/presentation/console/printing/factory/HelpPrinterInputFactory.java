package de.janbraunsdorff.ase.layer.presentation.console.printing.factory;

import de.janbraunsdorff.ase.layer.presentation.console.printing.part.Command;

public class HelpPrinterInputFactory extends PrinterInputFactory {

    private final int commandSize;

    public HelpPrinterInputFactory(int commandSize) {
        super();
        this.commandSize = commandSize;
    }

    public HelpPrinterInputFactory addHeadline(String text) {
        this.addText(text);
        this.addNewLine();
        return this;
    }

    public HelpPrinterInputFactory addCommand(String command, String description) {
        this.addCommand();
        this.addText(String.format("%-" + commandSize + "s\t%s", command, description));
        this.addNewLine();
        return this;
    }

    public PrinterInputFactory addCommand() {
        this.pieces.add(new Command());
        return this;
    }
}
