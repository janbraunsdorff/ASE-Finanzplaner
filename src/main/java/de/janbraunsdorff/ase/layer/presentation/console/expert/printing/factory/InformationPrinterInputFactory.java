package de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory;

public class InformationPrinterInputFactory extends PrinterInputFactory {

    public InformationPrinterInputFactory() {
        super();
    }

    public InformationPrinterInputFactory addHeadline(String text) {
        this.addInfoText(text);
        this.addNewLine();
        return this;
    }

    public InformationPrinterInputFactory addInformation(String text) {
        this.addInfoText(text);
        this.addNewLine();
        return this;
    }
}
