package de.janbraunsdorff.ase.layer.presentation.console.printer;

public class InformationOutputBuilder extends OutputBuilder {

    public InformationOutputBuilder() {
        super();
    }

    public InformationOutputBuilder addHeadline(String text) {
        this.addInfoText(text);
        this.addNewLine();
        return this;
    }

    public InformationOutputBuilder addInformation(String text) {
        this.addInfoText(text);
        this.addNewLine();
        return this;
    }
}
