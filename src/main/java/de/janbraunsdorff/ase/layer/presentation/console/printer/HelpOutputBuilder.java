package de.janbraunsdorff.ase.layer.presentation.console.printer;

import de.janbraunsdorff.ase.layer.presentation.console.printer.part.Command;

public class HelpOutputBuilder extends OutputBuilder {

    private final int commandSize;

    public HelpOutputBuilder(int commandSize) {
        super();
        this.commandSize = commandSize;
    }

    public HelpOutputBuilder addHeadline(String text) {
        this.addText(text);
        this.addNewLine();
        return this;
    }

    public HelpOutputBuilder addCommand(String command, String description) {
        this.addCommand();
        this.addText(String.format("%-" + commandSize + "s\t%s", command, description));
        this.addNewLine();
        return this;
    }

    public OutputBuilder addCommand() {
        this.pieces.add(new Command());
        return this;
    }
}
