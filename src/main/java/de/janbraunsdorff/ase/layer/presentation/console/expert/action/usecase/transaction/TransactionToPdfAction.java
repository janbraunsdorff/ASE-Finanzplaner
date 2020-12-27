package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetInIntervalQuery;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.PdfDocument;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.MonthSummary;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.MonthlySummary;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

        // TODO total income per month vs total expense per month

        String account = command.getParameter("-a");
        String accountName = accountService.getAccount(new AccountGetByAcronymQuery(account)).getName();
        LocalDate start = parseDate(command.getParameter("-s"));
        LocalDate end = parseDate(command.getParameter("-e"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMM");
        String name = accountName + " " + start.format(dtf) + end.format(dtf);
        PdfDocument doc = new PdfDocument(name);

        int numberOfChapters = 0;
        do {
            MonthlySummary pdf = getMonthlySummary(account, accountName, start);
            if (pdf != null) {
                doc.appendChapter(pdf);
            }
            start = start.plusMonths(1);
            numberOfChapters++;
        } while (start.isBefore(end));
        start = parseDate(command.getParameter("-s"));

        if (numberOfChapters > 1) {
            MonthSummary summary = getMonthSummary(start, end, account, accountName);
            doc.prependChapter(summary);
        }

        Path uri = doc.saveTo(name);
        Desktop.getDesktop().browse(uri.toUri());

        return new TransactionToPdfResult(uri.toAbsolutePath().toString());
    }

    private MonthlySummary getMonthlySummary(String account, String accountName, LocalDate start) throws AccountNotFoundException {
        List<TransactionDTO> transaction = getTransactionInInterval(
                account,
                start,
                LocalDate.of(start.getYear(), start.getMonthValue(), start.lengthOfMonth())
        );

        if (transaction.isEmpty()){
            return null;
        }

        Integer startValue = getStartValue(account, start);

        return new MonthlySummary(
                transaction,
                startValue,
                new ArrayList<String>() {{
                    add(accountName);
                }},
                accountService
        );
    }

    @NotNull
    private MonthSummary getMonthSummary(LocalDate start, LocalDate end, String account, String accountName) throws AccountNotFoundException {
        List<TransactionDTO> transaction = getTransactionInInterval(account, start, end);
        Integer startValue = getStartValue(account, start);

        return new MonthSummary(
                transaction,
                startValue,
                new ArrayList<String>() {{
                    add(accountName);
                }},
                start,
                end
        );
    }

    private List<TransactionDTO> getTransactionInInterval(String account, LocalDate start, LocalDate end) throws AccountNotFoundException {
        return this.service.getTransactions(
                new TransactionGetInIntervalQuery(
                        account,
                        start,
                        end
                )
        );
    }

    private Integer getStartValue(String account, LocalDate start) throws AccountNotFoundException {
        return getTransactionInInterval(
                account,
                LocalDate.of(0, 1, 1),
                start.minusDays(1)
        )
        .stream()
        .map(TransactionDTO::getValue)
        .reduce(0, Integer::sum);
    }

    private LocalDate parseDate(String date) {
        int intDate = Integer.parseInt(date);
        int year = intDate % 10_000;
        int month = (intDate / 10_000) % 100;
        int day = (intDate / 1_000_000) % 100;

        return LocalDate.of(year, month, day);
    }
}
