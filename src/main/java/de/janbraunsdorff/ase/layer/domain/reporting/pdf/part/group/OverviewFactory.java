package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group;

import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

import java.util.List;

public class OverviewFactory {
    private int totalIncome;
    private int totalIncomeSalary;
    private int totalIncomeContract;
    private int totalIncomeOthers;

    private int totalExpenses;
    private int totalExpensesMonthly;
    private int totalExpensesContract;
    private int totalExpensesPurchase;
    private int totalExpensesOthers;

    public OverviewFactory sort(List<TransactionDTO> transaction){
        transaction.forEach(t ->{
            // income
            if(t.getValue() > 0) {
                if (t.getCategory().equals("Gehalt")) {
                    this.addSalary(t.getValue());
                }else if(t.getContract()){
                    this.addIncomeContract(t.getValue());
                }else{
                    this.addOtherIncome(t.getValue());
                }
            }
            // outcome
            else if (t.getValue() < 0){
                if(t.getCategory().contains("Einkauf")){
                    this.addPurchase(t.getValue());
                }else if (t.getContract()) {
                    this.addExpensesContract(t.getValue());
                }else {
                    this.addExpensesOutcome(t.getValue());
                }
            }
        });
        return this;
    }

    private void addSalary(int value){
        this.totalIncomeSalary += value;
        this.totalIncome += value;
    }

    private void addIncomeContract(int value){
        this.totalIncomeContract += value;
        this.totalIncome += value;
    }

    private void addOtherIncome(int value) {
        this.totalIncomeOthers += value;
        this.totalIncome += value;
    }

    private void addMonthlyExpenses(int value){
        this.totalExpensesMonthly += value;
        this.totalExpenses += value;
    }

    private void addExpensesContract(int value){
        this.totalExpensesContract += value;
        this.totalExpenses += value;
    }

    private void addPurchase(int value){
        this.totalExpensesPurchase += value;
        this.totalExpenses += value;
    }

    private void addExpensesOutcome(int value){
        this.totalExpensesOthers += value;
        this.totalExpenses += value;
    }

    public Overview build(){
        return new Overview(
                this.totalIncome,
                this.totalIncomeSalary,
                this.totalIncomeContract,
                this.totalIncomeOthers,
                this.totalExpenses,
                this.totalExpensesMonthly,
                this.totalExpensesContract,
                this.totalExpensesPurchase,
                this.totalExpensesOthers,
                this.totalIncome - (this.totalExpenses * -1)
        );
    }

}
