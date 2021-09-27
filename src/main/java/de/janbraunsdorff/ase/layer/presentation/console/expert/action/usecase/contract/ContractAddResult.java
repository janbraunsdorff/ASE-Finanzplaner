package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.contract;

import de.janbraunsdorff.ase.layer.domain.contract.data.ContractDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.InformationPrinterInputFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContractAddResult implements Result {
    private ContractDTO storedContract;

    public ContractAddResult(ContractDTO storedContract) {
        this.storedContract = storedContract;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Ein neuer Vetrag wurde angelegt")
                .addInformation(String.format("Name: %s | Start am: %s | Endet am: %s | erwarteter Betrag: %s",
                        this.storedContract.name(), dateToString(this.storedContract.start()), dateToString(this.storedContract.end()),
                        this.storedContract.expectedAmount().getFormatted())
                )
                .build();
    }

    private String dateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
