package de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.group;

import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.presentation.console.expert.export.pdf.part.PdfPart;

import java.io.IOException;
import java.util.UUID;

public final class Overview implements PdfPart {
    private final int totalIncome;
    private final int totalIncomeSalary;
    private final int totalIncomeContract;
    private final int totalIncomeOthers;

    private final int totalExpenses;
    private final int totalExpensesMonthly;
    private final int totalExpensesContract;
    private final int totalExpensesPurchase;
    private final int totalExpensesOthers;

    private final int profit;

    public Overview(int totalIncome, int totalIncomeSalary, int totalIncomeContract, int totalIncomeOthers, int totalOutcome, int totalOutcomeMonthly, int totalOutcomeContract, int totalOutcomePurchase, int totalOutcomeOthers, int profit) {
        this.totalIncome = totalIncome;
        this.totalIncomeSalary = totalIncomeSalary;
        this.totalIncomeContract = totalIncomeContract;
        this.totalIncomeOthers = totalIncomeOthers;
        this.totalExpenses = totalOutcome;
        this.totalExpensesMonthly = totalOutcomeMonthly;
        this.totalExpensesContract = totalOutcomeContract;
        this.totalExpensesPurchase = totalOutcomePurchase;
        this.totalExpensesOthers = totalOutcomeOthers;
        this.profit = profit;
    }

    public HtmlObject render() throws IOException {
        HtmlObject html = getTemplate("overview.html");
        html.replace("income-total", String.valueOf(formatValue(this.totalIncome)));
        html.replace("income-salary", String.valueOf(formatValue(this.totalIncomeSalary)));
        html.replace("income-contract", String.valueOf(formatValue(this.totalIncomeContract)));
        html.replace("income-other", String.valueOf(formatValue(this.totalIncomeOthers)));

        html.replace("expense-total", String.valueOf(formatValue(this.totalExpenses * -1)));
        html.replace("expense-monthly", String.valueOf(formatValue(this.totalExpensesMonthly* -1)));
        html.replace("expense-contract", String.valueOf(formatValue(this.totalExpensesContract* -1)));
        html.replace("expense-purchase", String.valueOf(formatValue(this.totalExpensesPurchase* -1)));
        html.replace("expense-others", String.valueOf(formatValue(this.totalExpensesOthers* -1)));

        html.replace("expense-profit", String.valueOf(formatValue(this.profit)));
        html.replace("id", UUID.randomUUID().toString());
        html.replace("expense-value", String.valueOf(this.totalExpenses));
        html.replace("income-value", String.valueOf(this.totalIncome));

        html.replace("profit", String.valueOf(formatValue(this.profit)));
        html.replace("color", totalIncome + totalExpenses < 0 ? "style=\"color: #7a0c0c\"" : "");

        return html;


    }


}
