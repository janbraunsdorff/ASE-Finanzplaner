package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.*;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCategorizeMonthCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.BankCourseCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountMonthDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetLastQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("dashboard")
public class DashboardController {

    private final BankApplication bankApplication;
    private final AccountAnalyticsApplication accountAnalytics;
    private final TransactionApplication transactionApplication;

    public DashboardController(BankApplication bankApplication, AccountAnalyticsApplication accountAnalytics, TransactionApplication transactionApplication) {
        this.bankApplication = bankApplication;
        this.accountAnalytics = accountAnalytics;
        this.transactionApplication = transactionApplication;
    }

    @GetMapping("overview")
    public ResponseDashboardOverview overview() throws BankNotFoundException {
        Value total = new Value(0);
        Value account = new Value(0);
        Value deposit = new Value(0);
        Value bar = new Value(0);

        List<BankDTO> bankDTOS = this.bankApplication.get();
        for (BankDTO bank : bankDTOS) {
            switch (bank.type()) {
                case None -> bar = bar.add(bank.value());
                case Retail -> account = account.add(bank.value());
                case Investment -> deposit = deposit.add(bank.value());
            }

            total = total.add(bank.value());
        }

        return new ResponseDashboardOverview(
                total.getFormatted(),
                account.getFormatted(),
                deposit.getFormatted(),
                bar.getFormatted(),
                total.getValue(),
                deposit.getValue(),
                bar.getValue()
        );
    }

    @GetMapping("course")
    public List<ResponseDashboardCourse> course() throws BankNotFoundException, AccountNotFoundException {

        List<Value>[] typeValues = new List[2];
        typeValues[0] = getZeroList(12);
        typeValues[1] = getZeroList(12);

        for (BankDTO bank : this.bankApplication.get()) {
            if (bank.type().equals(BankType.None)) {
                continue;
            }

            if (bank.type().equals(BankType.Investment)){
                List<Value> course = accountAnalytics.getCourseOfTheLastMonths(new BankCourseCommand(12, bank.acronym()));
                for (int i = 0; i < course.size(); i++) {
                    Value v = course.get(i);
                    typeValues[1].add(i, typeValues[1].get(i).add(v));
                }
            }else if (bank.type().equals(BankType.Retail)) {
                List<Value> course = accountAnalytics.getCourseOfTheLastMonths(new BankCourseCommand(12, bank.acronym()));
                for (int i = 0; i < course.size(); i++) {
                    Value v = course.get(i);
                    typeValues[0].add(i, typeValues[0].get(i).add(v));
                }
            }

        }

        List<ResponseDashboardCourse> course = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            course.add(new ResponseDashboardCourse(typeValues[0].get(i).getValue(), typeValues[1].get(i).getValue()));
        }

        return course;


    }

    @GetMapping("month")
    public ResponseDashboardMonth month(){
        AccountMonthDTO month = accountAnalytics.getMonth(new AccountCategorizeMonthCommand(LocalDate.now()));

        return new ResponseDashboardMonth(
                month.profit().getFormatted(),
                month.percent(),

                month.totalIncome().getValue(),
                month.totalExpenses().getValue(),

                month.totalIncome().getFormatted(),
                month.totalIncomeSalary().getFormatted(),
                month.totalIncomeContract().getFormatted(),
                month.totalIncomeOthers().getFormatted(),

                month.totalExpenses().getFormatted(),
                month.totalExpensesMonthly().getFormatted(),
                month.totalExpensesContract().getFormatted(),
                month.totalExpensesPurchase().getFormatted(),
                month.totalExpensesOthers().getFormatted()
        );
    }


    @GetMapping("last-transactions")
    public List<ResponseDashboardLastTransaction> lastTransactions(){
        var mapping = accountAnalytics.getAcronymToNameMapping();
        return transactionApplication.getLast(new TransactionGetLastQuery(9))
                .stream()
                .map(t -> new ResponseDashboardLastTransaction(
                        t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        t.getThirdParty(),
                        t.getValue().getFormatted(),
                        mapping.get(t.getAccount()),
                        t.getCategory()))
                .collect(Collectors.toList());
    }

    private List<Value> getZeroList(int count){
        ArrayList<Value> values = new ArrayList<>();

        for (int i = 0; i <count; i++) {
            values.add(new Value(0));
        }

        return values;
    }

}
