package de.janbraunsdorff.ase.layer.presentation.console.printing.factory;

public class ErrorPrinterInputFactory extends PrinterInputFactory {
    public ErrorPrinterInputFactory() {
        super();
    }

    public ErrorPrinterInputFactory addError(String text) {
        this.addErrorText("Ups, ein Fehler ist aufgetreten");
        this.addNewLine();
        this.addErrorText(text);
        this.addNewLine();
        return this;
    }
}
