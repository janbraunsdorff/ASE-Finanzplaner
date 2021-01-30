package de.janbraunsdorff.ase.layer.domain.reporting.pdf.chapter;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountsGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course.MonthlyCourse;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.course.TotalMonthlyCourse;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group.IncomeExpenses;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group.OverviewFactory;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.headline.Headline;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.headline.HeadlineSize;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.posting.PostingItemsPages;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonthlySummary extends PdfChapter {
    private final String headline;
    private final String accountsString;
    private final PdfPage overview;
    private final PdfPage course;
    private final List<PdfPage> postingItems;


    public MonthlySummary(List<String> accounts, AccountIOApplication accountService, LocalDate start, LocalDate end, TransactionApplication service) throws AccountNotFoundException, BankNotFoundException {
        super(service);
        List<TransactionDTO> transactions = getTransactionInInterval(start, end, accounts);
        Value startValue = getStartValue(start, accounts);
        if (transactions.isEmpty()){
            throw new IllegalArgumentException("no transactions");
        }
        transactions.sort(Comparator.comparing(TransactionDTO::getDate));

        LocalDate dateOfFirstTransaction = transactions.get(0).getDate();
        int year = dateOfFirstTransaction.getYear();
        int month = dateOfFirstTransaction.getMonthValue();
        String monthName = DateFormatSymbols.getInstance().getMonths()[month - 1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startInterval = dateOfFirstTransaction.withDayOfMonth(1).format(formatter);
        String endInterval = dateOfFirstTransaction.withDayOfMonth(dateOfFirstTransaction.lengthOfMonth()).format(formatter);

        this.headline = String.join(" ","Monatsübersicht", monthName, String.valueOf(year));
        this.accountsString = accountService.getAccount(new AccountsGetByAcronymQuery(accounts)).stream().map(AccountDTO::getName).collect(Collectors.joining(";"));
        String interval = String.join(" - ", startInterval, endInterval);


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
        new PostingItemsPages(transactions, accountService).getPages().forEach(f -> this.postingItems.add(new PdfPage(f)));
    }


    @Override
    public HtmlObject render() {
        List<HtmlObject> objects = new ArrayList<>();
        objects.add(getPage(this.overview.render()));
        objects.add(getPage(this.course.render()));
        this.postingItems.forEach(f -> objects.add(getPage(f.render())));

        return HtmlObject.join(objects);
    }

    private HtmlObject getPage(HtmlObject s)  {
        HtmlObject template = getTemplate("page.html");
        template.replace("content", s);
        template.replace("chapter-title", this.headline);
        template.replace("accounts", this.accountsString);
        return template;
    }
}
