package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.*;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetInIntervalQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetLastQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("dashboard")
public class DashboardController {

    private final BankApplication bankApplication;
    private final AccountApplication accountApplication;
    private final TransactionApplication transactionApplication;

    public DashboardController(BankApplication bankApplication, AccountApplication accountApplication, TransactionApplication transactionApplication) {
        this.bankApplication = bankApplication;
        this.accountApplication = accountApplication;
        this.transactionApplication = transactionApplication;
    }

    @GetMapping("overview")
    public ResponseDashboardOverview overview() throws BankNotFoundException {
        Value total = new Value(0);
        Value account = new Value(0);
        Value deposit = new Value(0);
        Value bar = new Value(0);

        for (BankDTO bank : this.bankApplication.get()) {
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
        typeValues[0] = new ArrayList<Value>();
        typeValues[1] = new ArrayList<Value>();

        for (BankDTO bank : this.bankApplication.get()) {
            if (bank.type().equals(BankType.None)) {
                continue;
            }

            if (bank.type().equals(BankType.Investment)){
                typeValues[1] = accountApplication.getCourse(new BankCourseCommand(12, bank.acronym()));
            }else if (bank.type().equals(BankType.Retail)) {
                typeValues[0] = accountApplication.getCourse(new BankCourseCommand(12, bank.acronym()));
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
        AccountMonthDTO month = accountApplication.getMonth(new AccountCategorizeMonthCommand(LocalDate.now()));

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
        return transactionApplication.getLast(new TransactionGetLastQuery(9))
                .stream()
                .map(t -> new ResponseDashboardLastTransaction(
                        t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        t.getThirdParty(),
                        t.getValue().getFormatted(), t.getAccount(), t.getCategory()))
                .collect(Collectors.toList());

    }

}
