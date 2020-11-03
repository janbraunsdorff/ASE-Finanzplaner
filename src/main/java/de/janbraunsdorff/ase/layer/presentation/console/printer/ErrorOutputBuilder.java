package de.janbraunsdorff.ase.layer.presentation.console.printer;

public class ErrorOutputBuilder extends OutputBuilder {
    public ErrorOutputBuilder() {
        super();
    }

    public ErrorOutputBuilder addError(String text) {
        this.addErrorText("Ups, ein Fehler ist aufgetreten");
        this.addNewLine();
        this.addErrorText(text);
        this.addNewLine();
        return this;
    }
}
