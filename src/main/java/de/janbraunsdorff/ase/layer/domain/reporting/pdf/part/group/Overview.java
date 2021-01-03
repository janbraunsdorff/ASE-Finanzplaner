package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.HtmlObject;
import de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.PdfPart;

import java.util.UUID;

public final class Overview implements PdfPart {
    private final Value totalIncome;
    private final Value totalIncomeSalary;
    private final Value totalIncomeContract;
    private final Value totalIncomeOthers;

    private final Value totalExpenses;
    private final Value totalExpensesMonthly;
    private final Value totalExpensesContract;
    private final Value totalExpensesPurchase;
    private final Value totalExpensesOthers;

    private final Value profit;

    public Overview(Value totalIncome, Value totalIncomeSalary, Value totalIncomeContract, Value totalIncomeOthers, Value totalOutcome, Value totalOutcomeMonthly, Value totalOutcomeContract, Value totalOutcomePurchase, Value totalOutcomeOthers, Value profit) {
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

    public HtmlObject render() {
        HtmlObject html = getTemplate("overview.html");
        html.replace("income-total", this.totalIncome.getFormatted());
        html.replace("income-salary", this.totalIncomeSalary.getFormatted());
        html.replace("income-contract", this.totalIncomeContract.getFormatted());
        html.replace("income-other", this.totalIncomeOthers.getFormatted());

        html.replace("expense-total", this.totalExpenses.negated().getFormatted());
        html.replace("expense-monthly", this.totalExpensesMonthly.negated().getFormatted());
        html.replace("expense-contract", this.totalExpensesContract.negated().getFormatted());
        html.replace("expense-purchase", this.totalExpensesPurchase.negated().getFormatted());
        html.replace("expense-others", this.totalExpensesOthers.negated().getFormatted());

        html.replace("expense-profit", this.profit.getFormatted());
        html.replace("id", UUID.randomUUID().toString());
        html.replace("expense-value", String.valueOf(this.totalExpenses.getValue()));
        html.replace("income-value", String.valueOf(this.totalIncome.getValue()));

        html.replace("profit", this.profit.getFormatted());
        html.replace("color", totalIncome.getValue() + totalExpenses.getValue() < 0 ? "style=\"color: #7a0c0c\"" : "");

        return html;


    }


}
