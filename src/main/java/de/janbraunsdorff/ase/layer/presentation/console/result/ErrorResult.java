package de.janbraunsdorff.ase.layer.presentation.console.result;


import de.janbraunsdorff.ase.layer.presentation.console.printer.ErrorOutputBuilder;

public class ErrorResult implements Result {

    private final String errorText;

    public ErrorResult(String errorText){
        this.errorText = errorText;
    }

    @Override
    public String print() {
        return new ErrorOutputBuilder()
                .addError(this.errorText)
                .build();
    }
}
