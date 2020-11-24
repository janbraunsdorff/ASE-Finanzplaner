package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.InformationPrinterInputFactory;

public class AccountDeleteResult implements Result {
    private final String id;

    public AccountDeleteResult(String id) {
        this.id = id;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Ein Account wurde gelöscht")
                .addInformation(String.format("Account mit der Kennung: %s wurde gelöscht", this.id))
                .build();
    }
}
