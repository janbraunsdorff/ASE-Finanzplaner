package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.chapter;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.course.MonthCourse;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.course.TotalMonthCourse;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.group.IncomeExpenses;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.group.OverviewFactory;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.headline.Headline;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.headline.HeadlineSize;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MonthSummary implements PdfChapter {
    private final String headline;
    private final String accountsString;
    private final String interval;
    private final PdfPage overview;
    private final PdfPage course;
    private final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public MonthSummary(List<TransactionDTO> transactions, int startValue, List<String> accounts, LocalDate start, LocalDate end) {
        transactions.sort(Comparator.comparing(TransactionDTO::getDate));


        String monthNameStart = DateFormatSymbols.getInstance().getMonths()[start.getMonthValue() - 1];
        String monthNameEnd = DateFormatSymbols.getInstance().getMonths()[end.getMonthValue() - 1];

        this.headline = String.join(" ",monthNameStart, String.valueOf(start.getYear()), "-", monthNameEnd, String.valueOf(end.getYear()));
        this.accountsString = String.join(";", accounts);
        this.interval = String.join(" - ", start.format(formatter), end.format(formatter));


        this.overview = new PdfPage(
                new Headline(headline, HeadlineSize.H1),
                new Headline(interval, HeadlineSize.H4),
                new OverviewFactory().sort(transactions).build(),
                new IncomeExpenses(transactions)
        );

        this.course = new PdfPage(
                new MonthCourse(transactions, start, end),
                new TotalMonthCourse(transactions, startValue, start, end),
                new TotalMonthCourse(transactions, startValue, start, end)
        );
    }


    @Override
    public HtmlObject render() throws IOException {
        List<HtmlObject> objects = new ArrayList<>();
        objects.add(getPage(this.overview.render()));
        objects.add(getPage(this.course.render()));
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
