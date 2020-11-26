package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.InformationPrinterInputFactory;

public class BankDeleteResult implements Result {

    private final String id;

    public BankDeleteResult(String id) {
        this.id = id;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Eine Bank wurde gelöscht")
                .addInformation(String.format("Bank mit der Kennung: %s wurde gelöscht", this.id))
                .build();
    }
}
