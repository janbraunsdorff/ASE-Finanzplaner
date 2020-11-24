package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.printing.factory.InformationPrinterInputFactory;

public class BankAddResult implements Result {

    private final BankDTO bankEntity;

    public BankAddResult(BankDTO bankEntity) {
        this.bankEntity = bankEntity;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Eine neune Bank wurde angelget")
                .addInformation(String.format("Name: %s | Abkürzung: %s", bankEntity.getName(), bankEntity.getAcronym()))
                .build();

    }
}
