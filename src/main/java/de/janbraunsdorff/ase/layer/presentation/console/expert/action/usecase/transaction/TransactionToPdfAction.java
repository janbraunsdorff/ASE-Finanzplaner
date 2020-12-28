package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.PdfDocument;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.MonthSummary;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.MonthlySummary;

import java.awt.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class TransactionToPdfAction implements UseCase {

    private final TransactionApplication service;
    private final AccountApplication accountService;
    private String account;
    private PdfDocument doc;

    public TransactionToPdfAction(TransactionApplication service, AccountApplication accountService) {
        this.service = service;
        this.accountService = accountService;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a", "-s", "-e")) {
            return new TransactionHelpResult();
        }
        this.account = command.getParameter("-a");
        String accountName = this.accountService.getAccount(new AccountGetByAcronymQuery(account)).getName();


        LocalDate start = parseDate(command.getParameter("-s"));
        LocalDate end = parseDate(command.getParameter("-e"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMM");
        String name = accountName + " " + start.format(dtf) + end.format(dtf);
        this.doc = new PdfDocument(name);

        int numberOfChapters = addMonthlyChapters(start, end);
        start = parseDate(command.getParameter("-s"));

        addSummery(start, end, numberOfChapters);

        Path uri = doc.saveTo(name);
        Desktop.getDesktop().browse(uri.toUri());

        return new TransactionToPdfResult(uri.toAbsolutePath().toString());
    }

    private void addSummery(LocalDate start, LocalDate end, int numberOfChapters) throws AccountNotFoundException, BankNotFoundException {
        if (numberOfChapters > 1) {
            MonthSummary summary = new MonthSummary(
                   Collections.singletonList(account),
                    start,
                    end,
                    service,
                    accountService
            );
            doc.prependChapter(summary);
        }
    }

    private int addMonthlyChapters(LocalDate start, LocalDate end) throws AccountNotFoundException, BankNotFoundException {
        int numberOfChapters = 0;
        do {
            MonthlySummary pdf = new MonthlySummary(
                    Collections.singletonList(account),
                    accountService,
                    start,
                    LocalDate.of(start.getYear(), start.getMonthValue(), start.lengthOfMonth()),
                    service
            );
            doc.appendChapter(pdf);

            start = start.plusMonths(1);
            numberOfChapters++;
        } while (start.isBefore(end));

        return numberOfChapters;
    }

    private LocalDate parseDate(String date) {
        int intDate = Integer.parseInt(date);
        int year = intDate % 10_000;
        int month = (intDate / 10_000) % 100;
        int day = (intDate / 1_000_000) % 100;

        return LocalDate.of(year, month, day);
    }
}
