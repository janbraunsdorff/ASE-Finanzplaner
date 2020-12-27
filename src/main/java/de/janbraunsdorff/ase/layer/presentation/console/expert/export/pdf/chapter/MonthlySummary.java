package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.*;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MonthlySummary implements PdfChapter {
    private final String headline;
    private final String accountsString;
    private final String interval;
    private final PdfPage overview;
    private final PdfPage course;
    private final List<PdfPage> postingItems;
    private final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public MonthlySummary(List<TransactionDTO> transactions, int startValue, List<String> accounts) {
        transactions.sort(Comparator.comparing(TransactionDTO::getDate));

        LocalDate dateOfFirstTransaction = transactions.get(0).getDate();
        int year = dateOfFirstTransaction.getYear();
        int month = dateOfFirstTransaction.getMonthValue();
        String monthName = DateFormatSymbols.getInstance().getMonths()[month - 1];

        String startInterval = dateOfFirstTransaction.withDayOfMonth(1).format(formatter);
        String endInterval = dateOfFirstTransaction.withDayOfMonth(dateOfFirstTransaction.lengthOfMonth()).format(formatter);

        this.headline = String.join(" ","Monatsübersicht", monthName, String.valueOf(year));
        this.accountsString = String.join(";", accounts);
        this.interval = String.join(" - ", startInterval, endInterval);


        this.overview = new PdfPage(
                new Headline(headline, HeadlineSize.H1),
                new Headline(interval, HeadlineSize.H4),
                new OverviewFactory().sort(transactions).build(),
                new IncomeExpenses(transactions)
        );

        this.course = new PdfPage(
                new Headline("Verlauf", HeadlineSize.H1),
                new MonthlyCourse(transactions),
                new TotalMonthlyCourse(transactions, startValue)
        );

        this.postingItems = new ArrayList<>();
        new PostingItemsPages(transactions).getPages().forEach(f -> this.postingItems.add(new PdfPage(f)));
    }


    @Override
    public HtmlObject render() throws IOException {
        List<HtmlObject> objects = new ArrayList<>();
        objects.add(getPage(this.overview.render()));
        objects.add(getPage(this.course.render()));
        this.postingItems.forEach(f -> {
            try {
                objects.add(getPage(f.render()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return HtmlObject.join(objects);
    }

    private HtmlObject getPage(HtmlObject s) throws IOException {
        HtmlObject template = getTemplate("page.html");
        template.replace("content", s);
        template.replace("chapter-title", this.headline);
        template.replace("accounts", this.accountsString);
        return template;
    }
}
