package de.janbraunsdorff.ase.layer.domain.reporting.pdf;

import java.time.LocalDate;
import java.util.Collections;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter.MonthSummary;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter.MonthlySummary;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;

public class PdfDocumentBuilder {
    private final PdfDocument doc;
    private final TransactionApplication transactionService;
    private final AccountIOApplication accountService;
    private int numberOfChapters = 0;

    public PdfDocumentBuilder(String name, TransactionApplication service, AccountIOApplication accountService) {
        this.transactionService = service;
        this.accountService = accountService;
        this.doc = new PdfDocument(name);
    }

    public PdfDocumentBuilder addMonthly(LocalDate start, LocalDate end, String account) throws BankNotFoundException, AccountNotFoundException {
        do {
            MonthlySummary pdf = new MonthlySummary(
                    Collections.singletonList(account),
                    this.accountService,
                    start,
                    LocalDate.of(start.getYear(), start.getMonthValue(), start.lengthOfMonth()),
                    this.transactionService
            );
            this.doc.appendChapter(pdf);

            start = start.plusMonths(1);
            this.numberOfChapters++;
        } while (start.isBefore(end));

        return this;
    }

    public PdfDocumentBuilder addMonthlySummery(LocalDate start, LocalDate end, String account) throws BankNotFoundException, AccountNotFoundException {
        if (this.numberOfChapters > 1) {
            MonthSummary summary = new MonthSummary(
                    Collections.singletonList(account),
                    start,
                    end,
                    this.transactionService,
                    this.accountService
            );
            this.doc.prependChapter(summary);
        }
        return this;
    }

    public PdfDocument build() {
        return this.doc;
    }
}
