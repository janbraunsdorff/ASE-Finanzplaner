package de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetInIntervalQuery;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;

import java.time.LocalDate;
import java.util.List;

public abstract class PdfChapter implements PdfPart {
    private final TransactionApplication service;

    public PdfChapter(TransactionApplication service) {
        this.service = service;
    }

    protected List<TransactionDTO> getTransactionInInterval(LocalDate start, LocalDate end, List<String> account) throws AccountNotFoundException {
        return this.service.getTransactions(
                new TransactionGetInIntervalQuery(
                        account,
                        start,
                        end
                )
        );
    }

    protected Value getStartValue(LocalDate start, List<String> account) throws AccountNotFoundException {
        return new Value(getTransactionInInterval(
                LocalDate.of(0, 1, 1),
                start.minusDays(1),
                account
        )
                .stream()
                .map(TransactionDTO::getValue)
                .map(Value::getValue)
                .reduce(0, Integer::sum)
        );
    }
}
