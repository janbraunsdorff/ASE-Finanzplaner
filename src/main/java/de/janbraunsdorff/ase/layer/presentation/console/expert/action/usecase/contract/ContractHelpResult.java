package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.HelpPrinterInputFactory;

public class ContractHelpResult implements Result {
    @Override
    public PrinterInput print() {
        return new HelpPrinterInputFactory(35)
                .addHeadline("Contract Help")
                .addCommand("add -a [account] -amount [expected]", "adds a new Contract")
                .addCommand("ls", "adds a new Contract")
                .build();
    }
}
