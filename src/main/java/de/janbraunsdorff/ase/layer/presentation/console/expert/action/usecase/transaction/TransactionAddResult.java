package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.InformationPrinterInputFactory;

import java.time.format.DateTimeFormatter;

public record TransactionAddResult(TransactionDTO dto) implements Result {

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Eine neune Transaktion wurde angelget")
                .addInformation(String.format("Betrag: %s | Datum: %s | Von/Nach: %s | Kategorie: %s | Vertrag: %s", dto.getValue().getFormatted(), dto.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), dto.getThirdParty(), dto.getCategory(), dto.getContract() ? "ja" : "nein"))
                .build();
    }
}
