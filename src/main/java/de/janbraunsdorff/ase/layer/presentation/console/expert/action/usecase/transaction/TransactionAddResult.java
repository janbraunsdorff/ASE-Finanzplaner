package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.PrinterInput;
import de.janbraunsdorff.ase.layer.presentation.console.expert.printing.factory.InformationPrinterInputFactory;

import java.time.format.DateTimeFormatter;

public class TransactionAddResult implements Result {
    private final TransactionDTO dto;

    public TransactionAddResult(TransactionDTO dto) {
        this.dto = dto;
    }

    @Override
    public PrinterInput print() {
        return new InformationPrinterInputFactory()
                .addHeadline("Eine neune Transaktion wurde angelget")
                .addInformation(String.format("Betrag: %d | Datum: %s | Von/Nach: %s | Kategorie: %s | Vertrag: %s", dto.getValue(), dto.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), dto.getThirdParty(), dto.getCategory(), dto.getContract() ? "ja" : "nein"))
                .build();
    }
}
