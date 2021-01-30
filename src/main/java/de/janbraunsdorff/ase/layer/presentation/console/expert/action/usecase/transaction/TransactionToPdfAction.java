package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.PdfDocument;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.PdfDocumentBuilder;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;

import java.awt.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionToPdfAction implements UseCase {

    private final TransactionApplication service;
    private final AccountApplication accountService;

    public TransactionToPdfAction(TransactionApplication service, AccountApplication accountService) {
        this.service = service;
        this.accountService = accountService;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a", "-s", "-e")) {
            return new TransactionHelpResult();
        }
        String account = command.getParameter("-a");
        String accountName = this.accountService.getAccount(new AccountGetByAcronymQuery(account)).getName();


        LocalDate start = parseDate(command.getParameter("-s"));
        LocalDate end = parseDate(command.getParameter("-e"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMM");
        String name = accountName + " " + start.format(dtf) + end.format(dtf);

        PdfDocument doc = new PdfDocumentBuilder(name, service, accountService)
                .addMonthly(start, end, account)
                .addMonthlySummery(parseDate(command.getParameter("-s")), end, account)
                .build();

        Path uri = doc.saveTo(name);
        if (Desktop.isDesktopSupported()){
            Desktop.getDesktop().browse(uri.toUri());
        }

        return new TransactionToPdfResult(uri.toAbsolutePath().toString());
    }

    private LocalDate parseDate(String date) {
        int intDate = Integer.parseInt(date);
        int year = intDate % 10_000;
        int month = (intDate / 10_000) % 100;
        int day = (intDate / 1_000_000) % 100;

        return LocalDate.of(year, month, day);
    }
}
