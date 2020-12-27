package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.PdfDocument;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.MonthlySummary;

import java.awt.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionToPdfAction implements UseCase {

    private final TransactionApplication service;
    private AccountApplication accountService;

    public TransactionToPdfAction(TransactionApplication service, AccountApplication accountService) {
        this.service = service;
        this.accountService = accountService;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        if (!command.areTagsAndValuesPresent("-a", "-s" , "-e")) {
            return new TransactionHelpResult();
        }

        String account = command.getParameter("-a");
        String accountName = accountService.getAccount(new AccountGetByAcronymQuery(account)).getName();
        LocalDate start = parseDate(command.getParameter("-s"));
        LocalDate end = parseDate(command.getParameter("-e"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMM");
        String name = accountName + " " + start.format(dtf) + end.format(dtf);
        PdfDocument doc = new PdfDocument(name);

        do {
            List<TransactionDTO> transaction = this.service.getTransactions(
                    new TransactionGetInIntervalQuery(
                            account,
                            start,
                            LocalDate.of(start.getYear(), start.getMonthValue(), start.lengthOfMonth())
                    )
            );
            Integer startValue = this.service.getTransactions(
                    new TransactionGetInIntervalQuery(
                            account,
                            LocalDate.of(0,1,1), start.minusDays(1)
                    )
            )
                .stream()
                .map(TransactionDTO::getValue)
                .reduce(0, Integer::sum);




            MonthlySummary pdf = new MonthlySummary(
                    transaction,
                    startValue,
                    new ArrayList<String>(){{
                        add(accountName);
                    }},
                    accountService
            );

            doc.addChapter(pdf);
            start = start.plusMonths(1);
        }while (start.isBefore(end));



        URI uri = doc.saveTo(name);
        Desktop.getDesktop().browse(uri);

        return new TransactionToPdfResult(uri.toString());
    }

    private LocalDate parseDate(String date){
        int intDate = Integer.parseInt(date);
        int year = intDate % 10_000;
        int month = (intDate / 10_000) % 100;
        int day = (intDate / 1_000_000) % 100;

        return LocalDate.of(year, month, day);

    }
}
