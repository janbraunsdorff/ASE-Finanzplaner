package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.InformationPrinterInputFactory;

public class TransactionDeleteResult implements Result {

    private final String id;

    public TransactionDeleteResult(String id) {
        this.id = id;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Eine Transaction wurde gelöscht")
                .addInformation(String.format("Transaction mit der Kennung: %s wurde gelöscht", this.id))
                .build();
    }
}
