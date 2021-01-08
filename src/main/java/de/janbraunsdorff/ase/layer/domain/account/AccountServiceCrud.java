package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.domain.*;
import de.janbraunsdorff.ase.layer.domain.bank.Bank;
import de.janbraunsdorff.ase.layer.domain.bank.BankRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceCrud implements AccountApplication {
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final BankRepository bankRepo;

    public AccountServiceCrud(AccountRepository repo, TransactionRepository transactionRepo, BankRepository bankRepo) {
        this.bankRepo = bankRepo;
        this.accountRepo = repo;
        this.transactionRepo = transactionRepo;
    }

    public List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        List<AccountDTO> accounts = new ArrayList<>();
        for (String s : query.acronym()) {
            AccountDTO account = getAccount(s);
            accounts.add(account);
        }
        return accounts;

    }

    @Override
    public AccountDTO getAccount(AccountGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException {
        return getAccount(query.acronym());
    }

    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException {
        checkIfBankExists(query.bankAcronym());
        List<AccountDTO> collect = this.accountRepo.getAccountsOfBankByBankAcronym(query.bankAcronym()).stream().map(a -> {
            int amount = -1;
            String bankName = "nicht bekannt";
            try {
                amount = Math.toIntExact(transactionRepo.count(a.getAcronym()));
                bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
            } catch (BankNotFoundException ignored) {

            }
            return new AccountDTO(a.getName(), a.getNumber(), amount, a.getAcronym(), new Value(transactionRepo.getValueOfAccount(a.getAcronym())), bankName);
        }).collect(Collectors.toList());

        if (collect.isEmpty()) {
            collect.add(new AccountDTO("---", "---", 0, "---", new Value(0), query.bankAcronym()));
        }
        return collect;
    }

    private Bank checkIfBankExists(String acronym) throws BankNotFoundException {
        return this.bankRepo.getBankByAcronym(acronym);
    }

    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws AcronymAlreadyExistsException, BankNotFoundException {
        Bank bank = checkIfBankExists(command.bank());
        Account account = new Account(command.bank(), command.name(), command.number(), command.acronym());
        this.accountRepo.createAccount(account);
        return new AccountDTO(account.getName(), account.getNumber(), 0, account.getAcronym(), new Value(0), bank.getName());
    }

    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException {
        for (Transaction t : this.transactionRepo.getTransactionOfAccount(command.accountAcronym(), -1)) {
            this.transactionRepo.deleteTransactionById(t.getId());
        }
        this.accountRepo.deleteAccountByAcronym(command.accountAcronym());
    }

    @Override
    public List<Value> getCourse(AccountCourseCommand command) {
        LocalDate startInterval = LocalDate.now().minusMonths(command.month());
        List<Value> values = new ArrayList<>();

        List<Transaction> transactions = transactionRepo.getTransactionOfAccount(Arrays.asList(command.accountAcronym()), LocalDate.MIN, LocalDate.MAX);

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
    public List<Value> getCourse(BankCourseCommand command) throws BankNotFoundException {
        String[] accountAcronyms = this.getAccountsOfBank(new AccountGetQuery(command.bankAcronym())).stream().map(AccountDTO::getAcronym).toArray(String[]::new);
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

    @NotNull
    private AccountDTO getAccount(String s) throws AccountNotFoundException, BankNotFoundException {
        Account a = this.accountRepo.getAccountByAcronym(s);
        int amount = Math.toIntExact(transactionRepo.count(a.getAcronym()));
        String bankName = bankRepo.getBankByAcronym(a.getBankAcronym()).getName();
        return new AccountDTO(
                a.getName(),
                a.getNumber(),
                amount,
                a.getAcronym(),
                new Value(transactionRepo.getValueOfAccount(a.getAcronym())),
                bankName
        );
    }
}
