package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCategorizeMonthCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCourseCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.BankCourseCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDetailDTO;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountMonthDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetDetailQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountAnalytics implements AccountAnalyticsApplication{

    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final AccountIOApplication io;
    private final BankRepository bankRepo;

    public AccountAnalytics(AccountRepository repo, TransactionRepository transactionRepo, BankRepository bankRepo, AccountIOApplication io) {
        this.bankRepo = bankRepo;
        this.accountRepo = repo;
        this.transactionRepo = transactionRepo;
        this.io = io;
    }

    @Override
    public List<Value> getCourse(AccountCourseCommand command) {
        LocalDate startInterval = LocalDate.now().minusMonths(command.month());
        List<Value> values = new ArrayList<>();

        List<Transaction> transactions = transactionRepo.getTransactionOfAccount(Arrays.asList(command.accountAcronym()), LocalDate.of(0, 1, 1), LocalDate.now());

        LocalDate finalStartInterval = startInterval;
        int startAccountValue = transactions.stream()
                .filter(a -> a.getDate().isBefore(finalStartInterval.plusDays(1)))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);


        for (int i = 0; i < command.month(); i++) {
            LocalDate finalStartInterval1 = startInterval;
            startAccountValue = transactions.stream()
                    .filter(a -> a.getDate().isAfter(finalStartInterval1) && a.getDate().isBefore(finalStartInterval1.plusMonths(1).plusDays(1)))
                    .map(Transaction::getValue)
                    .reduce(0, Integer::sum) + startAccountValue;

            values.add(new Value(startAccountValue));
            startInterval = startInterval.plusMonths(1);
        }

        return values;
    }

    @Override
    public List<Value> getCourse(BankCourseCommand command) throws BankNotFoundException, AccountNotFoundException {
        String[] accountAcronyms = this.io.getAccountsOfBank(new AccountGetQuery(command.bankAcronym())).stream().map(AccountDTO::getAcronym).toArray(String[]::new);
        return this.getCourse(new AccountCourseCommand(command.month(), accountAcronyms));

    }

    @Override
    public AccountMonthDTO getMonth(AccountCategorizeMonthCommand command) {
        List<Transaction> transactions = this.transactionRepo.getTransactions(LocalDate.of(command.month().getYear(), command.month().getMonth(), 1), LocalDate.of(command.month().getYear(), command.month().getMonth(), command.month().lengthOfMonth()));

        Value totalIncome = new Value(0);
        Value totalIncomeSalary = new Value(0);
        Value totalIncomeContract = new Value(0);
        Value totalIncomeOthers = new Value(0);

        Value totalExpenses = new Value(0);
        Value totalExpensesMonthly = new Value(0);
        Value totalExpensesContract = new Value(0);
        Value totalExpensesPurchase = new Value(0);
        Value totalExpensesOthers = new Value(0);

        for (Transaction t : transactions) {// income
            if (t.getValue() > 0) {
                totalIncome = totalIncome.add(t.getValue());
                if (t.getCategory().equals("Gehalt")) {
                    totalIncomeSalary = totalIncomeSalary.add(t.getValue());
                } else if (t.getContract()) {
                    totalIncomeContract = totalIncomeContract.add(t.getValue());
                } else {
                    totalIncomeOthers = totalIncomeOthers.add(t.getValue());
                }
            }
            // outcome
            else if (t.getValue() < 0) {
                totalExpenses = totalExpenses.add(t.getValue());
                if (t.getCategory().contains("Einkauf")) {
                    totalExpensesPurchase = totalExpensesPurchase.add(t.getValue());
                } else if (t.getContract()) {
                    totalExpensesContract = totalExpensesContract.add(t.getValue());
                } else {
                    totalExpensesOthers = totalExpensesOthers.add(t.getValue());
                }
            }
        }


        double percent = (double) (totalExpenses.getValue() * -1) / (double) Math.max(totalIncome.getValue(), 1);
        percent = percent > 1 ? percent - 1: percent;
        String printPercent = new DecimalFormat("0.00").format(percent * 100)+ "%";


        return new AccountMonthDTO(
                totalIncome, totalIncomeSalary, totalIncomeContract, totalIncomeOthers,
                totalExpenses, totalExpensesMonthly, totalExpensesContract, totalExpensesPurchase, totalExpensesOthers,

                totalIncome.add(totalExpenses),
                printPercent
        );
    }

    @Override
    public HashMap<String, String> getAcronymToNameMapping() {
        HashMap<String, String> mapping = new HashMap<>();
        accountRepo.getAll().forEach(t -> mapping.put(t.getAcronym(), t.getName()));
        return mapping;
    }

    @Override
    public AccountDetailDTO getAccountDetail(AccountGetDetailQuery query) {
        try {
            var accountByAcronym = this.accountRepo.getAccountByAcronym(query.accountAcronym());
            var value = this.transactionRepo.getValueOfAccount(query.accountAcronym());
            var last7 =  this.transactionRepo.getValueOfAccount(LocalDate.of(0,1,1), LocalDate.now().minusDays(8), Set.of(query.accountAcronym()));
            var last30 =  this.transactionRepo.getValueOfAccount(LocalDate.of(0,1,1), LocalDate.now().minusDays(31), Set.of(query.accountAcronym()));
            var max = this.transactionRepo.getMaxValueOfAccount(query.accountAcronym());
            var lastPostingDate = "01.01.0001";
            if (!this.transactionRepo.getTransactionOfAccount(query.accountAcronym(), 1).isEmpty()){
                lastPostingDate = this.transactionRepo.getTransactionOfAccount(query.accountAcronym(), 1).get(0).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }


            return new AccountDetailDTO(
                    accountByAcronym.getName(),
                    accountByAcronym.getAcronym(),
                    new Value(value).getFormatted(),
                    getPercent(last7, value),
                    getPercent(last30, value),
                    new Value(max).getFormatted(),
                    lastPostingDate,
                    getCourse(query.accountAcronym(), 365)
            );

        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Integer> getCourse(String accountAcronym, int days) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < days/10; i++){
            values.add(this.transactionRepo.getValueOfAccount(LocalDate.of(0,1,1), LocalDate.now().minusDays(days - (i*10)), Set.of(accountAcronym)));
        }

        values.add(this.transactionRepo.getValueOfAccount(LocalDate.of(0,1,1), LocalDate.now(), Set.of(accountAcronym)));

        return values;
    }

    private String getPercent(int base, int value){
        var d = (double) value / (double) base;
        d =  (d > 1)? (d -1) * 100: (1 - d) * -1;
        return new DecimalFormat("0.0000").format(d) + "%";
    }


}


