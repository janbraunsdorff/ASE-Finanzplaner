package de.janbraunsdorff.ase.layer.presentation.console.result;


import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;

public class ErrorResult implements Result {

    private final String errorText;

    public ErrorResult(String errorText){
        this.errorText = errorText;
    }

    @Override
    public String print() {
        return new OutputBuilder()
                .addErrorText(this.errorText)
                .addNewLine()
                .build();
    }
}
