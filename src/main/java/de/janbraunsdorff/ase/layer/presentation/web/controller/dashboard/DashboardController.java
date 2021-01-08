package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.AccountApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.bank.BankApplication;
import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.domain.bank.BankType;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetInIntervalQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
            switch (bank.getType()) {
                case None -> bar = bar.add(bank.getValue());
                case Retail -> account = account.add(bank.getValue());
                case Investment -> deposit = deposit.add(bank.getValue());
            }

            total = total.add(bank.getValue());
        }
        ;

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
            if (bank.getType().equals(BankType.None)) {
                continue;
            }

            LocalDate startInterval = LocalDate.now().minusMonths(12);
            List<String> accounts = this.accountApplication.getAccountsOfBank(new AccountGetQuery(bank.getAcronym())).stream().map(AccountDTO::getAcronym).collect(Collectors.toList());
            List<TransactionDTO> transactions = this.transactionApplication.getTransactions(new TransactionGetInIntervalQuery(accounts, LocalDate.MIN, LocalDate.MAX));

            LocalDate finalStartInterval = startInterval;
            Value reduce = transactions.stream()
                    .filter(a -> a.getDate().isBefore(finalStartInterval))
                    .map(TransactionDTO::getValue)
                    .reduce(new Value(0), Value::combine);

            for (int i = 0; i < 12; i++) {
                LocalDate finalStartInterval1 = startInterval;
                reduce = transactions.stream()
                        .filter(a -> a.getDate().isAfter(finalStartInterval1) && a.getDate().isBefore(finalStartInterval1.plusMonths(1).plusDays(1)))
                        .map(TransactionDTO::getValue)
                        .reduce(new Value(0), Value::combine).add(reduce);
                if (bank.getType().equals(BankType.Investment)){
                    typeValues[1].add(reduce);
                }else if (bank.getType().equals(BankType.Retail)){
                    typeValues[0].add(reduce);
                }
                startInterval = startInterval.plusMonths(1);
            }

        }

        List<ResponseDashboardCourse> course = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            course.add(new ResponseDashboardCourse(typeValues[0].get(i).getValue(), typeValues[1].get(i).getValue()));
        }

        return course;


    }
}
