package de.janbraunsdorff.ase.userinterface.console.result;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;

public class ErrorResult implements Result{

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
