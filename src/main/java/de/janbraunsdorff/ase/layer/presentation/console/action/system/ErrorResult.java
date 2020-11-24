package de.janbraunsdorff.ase.layer.presentation.console.action.system;


import de.janbraunsdorff.ase.layer.presentation.console.printer.ErrorOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class ErrorResult implements Result {

    private final String errorText;

    public ErrorResult(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public String print() {
        return new ErrorOutputBuilder()
                .addError(this.errorText)
                .build();
    }
}
