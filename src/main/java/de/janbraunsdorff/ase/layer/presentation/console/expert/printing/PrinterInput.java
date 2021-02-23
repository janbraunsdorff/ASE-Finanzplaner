package de.janbraunsdorff.ase.layer.presentation.console.expert.printing;

public final record PrinterInput(String output) {

    public String getStringToPrint() {
        return this.output;
    }
}
