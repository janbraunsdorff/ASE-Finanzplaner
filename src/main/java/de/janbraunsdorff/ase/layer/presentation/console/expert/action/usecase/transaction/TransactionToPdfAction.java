package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.transaction.*;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.UseCase;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.PdfDocument;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter.MonthlySummary;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionToPdfAction implements UseCase {

    private final TransactionApplication service;

    public TransactionToPdfAction(TransactionApplication service) {
        this.service = service;
    }

    @Override
    public Result act(ExpertCommand command) throws Exception {
        // transactions print -a VB -s 01012020 -e 31012020
        if (!command.areTagsAndValuesPresent("-a", "-s")) {
            return new TransactionHelpResult();
        }

        String account = command.getParameter("-a");
        LocalDate start = parseDate(command.getParameter("-s"));
        LocalDate end = parseDate(command.getParameter("-e"));

        List<TransactionDTO> transaction = this.service.getTransactions(new TransactionGetInIntervalQuery(account, start, end));
        Integer startValue = this.service.getTransactions(new TransactionGetInIntervalQuery(account, LocalDate.of(0,1,1), start.minusDays(1)))
                .stream()
                .map(TransactionDTO::getValue)
                .reduce(0, Integer::sum);


        String name = "";
        PdfDocument doc = new PdfDocument(name);

        MonthlySummary pdf = new MonthlySummary(transaction, startValue, new ArrayList<>());
        doc.addChapter(pdf);
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
