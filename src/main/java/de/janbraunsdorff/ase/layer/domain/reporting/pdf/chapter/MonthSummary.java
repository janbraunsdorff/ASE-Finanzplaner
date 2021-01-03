package de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountsGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course.MonthCourse;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course.TotalMonthCourse;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group.IncomeExpenses;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group.OverviewFactory;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.headline.Headline;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.headline.HeadlineSize;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonthSummary extends PdfChapter {
    private final String headline;
    private final String accountsString;
    private final PdfPage overview;
    private final PdfPage course;


    public MonthSummary(List<String> accounts, LocalDate start, LocalDate end, TransactionApplication service, AccountApplication accountService) throws AccountNotFoundException, BankNotFoundException {
        super(service);
        List<TransactionDTO> transactions = getTransactionInInterval(start, end, accounts);
        Value startValue = getStartValue(start, accounts);
        transactions.sort(Comparator.comparing(TransactionDTO::getDate));


        String monthNameStart = DateFormatSymbols.getInstance().getMonths()[start.getMonthValue() - 1];
        String monthNameEnd = DateFormatSymbols.getInstance().getMonths()[end.getMonthValue() - 1];

        this.headline = String.join(" ",monthNameStart, String.valueOf(start.getYear()), "-", monthNameEnd, String.valueOf(end.getYear()));
        this.accountsString = accountService.getAccount(new AccountsGetByAcronymQuery(accounts)).stream().map(AccountDTO::getName).collect(Collectors.joining(";"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String interval = String.join(" - ", start.format(formatter), end.format(formatter));


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
    public HtmlObject render() {
        List<HtmlObject> objects = new ArrayList<>();
        objects.add(getPage(this.overview.render()));
        objects.add(getPage(this.course.render()));
        return HtmlObject.join(objects);
    }

    private HtmlObject getPage(HtmlObject s) {
        HtmlObject template = getTemplate("page.html");
        template.replace("content", s);
        template.replace("chapter-title", this.headline);
        template.replace("accounts", this.accountsString);
        return template;
    }
}
