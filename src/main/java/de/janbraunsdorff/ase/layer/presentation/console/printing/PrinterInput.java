package de.janbraunsdorff.ase.layer.presentation.console.printing;

public final class PrinterInput {
    private final String output;

    public PrinterInput(String output) {
        this.output = output;
    }

    public String getStringToPrint() {
        return this.output;
    }
}
