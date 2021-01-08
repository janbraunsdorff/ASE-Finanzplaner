package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.InformationPrinterInputFactory;

public record TransactionToPdfResult(String path) implements Result {

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Das Document steht bereit unter:")
                .addInformation(path)
                .build();
    }
}
