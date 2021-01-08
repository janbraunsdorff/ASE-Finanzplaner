package de.janbraunsdorff.ase.layer.presentation.web.controller.dashboard;

public class ResponseDashboardMonth {
    private final String total;
    private final String percent;
    private final int income;
    private final int expense;
    private final String incomeTotal;
    private final String incomeSalary;
    private final String incomeContract;
    private final String incomeOther;
    private final String expenseTotal;
    private final String expenseMonthly;
    private final String expenseContract;
    private final String expensePurchase;
    private final String expenseOther;

    public ResponseDashboardMonth(
            String total, String percent, int income, int expense,
            String incomeTotal, String incomeSalary, String incomeContract, String incomeOther,
            String expenseTotal, String expenseMonthly, String expenseContract, String expensePurchase, String expenseOther) {
        this.total = total;
        this.percent = percent;
        this.income = income;
        this.expense = expense;
        this.incomeTotal = incomeTotal;
        this.incomeSalary = incomeSalary;
        this.incomeContract = incomeContract;
        this.incomeOther = incomeOther;
        this.expenseTotal = expenseTotal;
        this.expenseMonthly = expenseMonthly;
        this.expenseContract = expenseContract;
        this.expensePurchase = expensePurchase;
        this.expenseOther = expenseOther;
    }

    public String getTotal() {
        return total;
    }

    public String getPercent() {
        return percent;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public String getIncomeTotal() {
        return incomeTotal;
    }

    public String getIncomeSalary() {
        return incomeSalary;
    }

    public String getIncomeContract() {
        return incomeContract;
    }

    public String getIncomeOther() {
        return incomeOther;
    }

    public String getExpenseTotal() {
        return expenseTotal;
    }

    public String getExpenseMonthly() {
        return expenseMonthly;
    }

    public String getExpenseContract() {
        return expenseContract;
    }

    public String getExpensePurchase() {
        return expensePurchase;
    }

    public String getExpenseOther() {
        return expenseOther;
    }
}
