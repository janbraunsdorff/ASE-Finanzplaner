package de.janbraunsdorff.ase.layer.domain.analyse;

public class TransactionGroupDTO {
    private final String account;
    private final int month;
    private final int year;
    private int income;
    private int outcome;
    private int total;
    private int value;
    private int incomeContract;
    private int outcomeContract;

    public TransactionGroupDTO(String account, int month, int year) {
        this.account = account;
        this.month = month;
        this.year = year;
    }

    public void increment(int income) {
        this.income += income;
        this.total += income;
    }

    public void decrement(int outcome) {
        this.outcome += outcome;
        this.total += outcome;
    }

    public void addIncomeContract(int incomeContract){
        this.incomeContract += incomeContract;
    }

    public void addOutcomeContract(int outcomeContract){
        this.outcomeContract += outcomeContract;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getIncome() {
        return income;
    }

    public int getOutcome() {
        return outcome;
    }

    public int getTotal() {
        return total;
    }

    public String getAccount() {
        return account;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getIncomeContract() {
        return incomeContract;
    }

    public int getOutcomeContract() {
        return outcomeContract;
    }
}
