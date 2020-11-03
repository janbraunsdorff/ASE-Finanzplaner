package de.janbraunsdorff.ase.layer.presentation.console.result.bank;


import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class BankDeleteResult implements Result {

    private final String id;

    public BankDeleteResult(String id){
        this.id = id;
    }
    @Override
    public String print() {
        return new OutputBuilder()
                .addInfoText(String.format("Bank mit der Kennung: %s wurde gelöscht", this.id))
                .build();
    }
}
