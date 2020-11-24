package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;


import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.InformationPrinterInputFactory;

public class AccountAddResult implements Result {

    private final AccountDTO account;

    public AccountAddResult(AccountDTO account) {
        this.account = account;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Ein Account wurde erstellt")
                .addInformation(String.format("ID: %s | Name: %s | Abkürzung: %s", account.getAcronym(), account.getName(), account.getAcronym()))
                .build();
    }
}
