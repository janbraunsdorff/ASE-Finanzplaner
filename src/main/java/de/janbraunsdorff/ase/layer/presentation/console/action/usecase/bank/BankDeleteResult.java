package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public class BankDeleteResult implements Result {

    private final String id;

    public BankDeleteResult(String id) {
        this.id = id;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Eine Bank wurde gelöscht")
                .addInformation(String.format("Bank mit der Kennung: %s wurde gelöscht", this.id))
                .build();
    }
}
