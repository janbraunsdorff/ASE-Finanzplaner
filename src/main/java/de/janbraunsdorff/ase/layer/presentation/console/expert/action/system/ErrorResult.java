package de.janbraunsdorff.ase.layer.presentation.console.expert.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.ErrorPrinterInputFactory;

public record ErrorResult(String errorText) implements Result {

    @Override
    public PrinterInput print() {
        return new ErrorPrinterInputFactory()
                .addError(this.errorText)
                .build();
    }
}
