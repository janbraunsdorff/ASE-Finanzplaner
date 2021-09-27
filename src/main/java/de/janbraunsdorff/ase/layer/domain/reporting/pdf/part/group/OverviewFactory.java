package de.janbraunsdorff.ase.layer.domain.reporting.pdf.part.group;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;

public class OverviewFactory {
    private Value totalIncome = new Value(0);
    private Value totalIncomeSalary = new Value(0);
    private Value totalIncomeContract = new Value(0);
    private Value totalIncomeOthers = new Value(0);

    private Value totalExpenses = new Value(0);
    private Value totalExpensesMonthly = new Value(0);
    private Value totalExpensesContract = new Value(0);
    private Value totalExpensesPurchase = new Value(0);
    private Value totalExpensesOthers = new Value(0);


    public OverviewFactory sort(List<TransactionDTO> transaction){
        transaction.forEach(t ->{
            // income
            if(t.getValue().isPositive()) {
                if (t.getCategory().equals("Gehalt")) {
                    this.addSalary(t.getValue());
                }else if(t.getContract()){
                    this.addIncomeContract(t.getValue());
                }else{
                    this.addOtherIncome(t.getValue());
                }
            }
            // outcome
            else if (!t.getValue().isPositive()){
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

    private void addSalary(Value value){
        this.totalIncomeSalary = this.totalIncomeSalary.add(value);
        this.totalIncome =  this.totalIncome.add(value);
    }

    private void addIncomeContract(Value value){
        this.totalIncomeContract = this.totalIncomeContract.add(value);
        this.totalIncome = this.totalIncome.add(value);
    }

    private void addOtherIncome(Value value) {
        this.totalIncomeOthers = this.totalIncomeOthers.add(value);
        this.totalIncome = this.totalIncome.add(value);
    }

    private void addMonthlyExpenses(Value value){
        this.totalExpensesMonthly = this.totalExpensesMonthly.add(value);
        this.totalExpenses = this.totalExpenses.add(value);
    }

    private void addExpensesContract(Value value){
        this.totalExpensesContract = this.totalExpensesContract.add(value);
        this.totalExpenses = this.totalExpenses.add(value);
    }

    private void addPurchase(Value value){
        this.totalExpensesPurchase = this.totalExpensesPurchase.add(value);
        this.totalExpenses = this.totalExpenses.add(value);
    }

    private void addExpensesOutcome(Value value){
        this.totalExpensesOthers =  this.totalExpensesOthers.add(value);
        this.totalExpenses = this.totalExpenses.add(value);
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
                this.totalIncome.add(this.totalExpenses)
        );
    }

}
