package de.janbraunsdorff.ase.layer.presentation.console.expert.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.ErrorPrinterInputFactory;

public class ErrorResult implements Result {

    private final String errorText;

    public ErrorResult(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public PrinterInput print() {
        return new ErrorPrinterInputFactory()
                .addError(this.errorText)
                .build();
    }
}
