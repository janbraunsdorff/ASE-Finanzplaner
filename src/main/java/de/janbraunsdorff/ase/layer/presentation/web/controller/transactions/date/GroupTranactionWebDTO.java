package de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupDTO;

public class GroupTranactionWebDTO {
    private String name;
    private String income;
    private String outcome;
    private String total;
    private String value;
    private String incomeContract;
    private String outcomeContract;
    private String start;
    private String end;

    public GroupTranactionWebDTO(int month, int year, String income, String outcome, String total, String value, String incomeContract, String outcomeContract, String start, String end) {
        this.start = start;
        this.end = end;
        this.name = month + "/" + year;
        this.income = income;
        this.outcome = outcome;
        this.total = total;
        this.value = value;
        this.incomeContract = incomeContract;
        this.outcomeContract = outcomeContract;
    }

    public  GroupTranactionWebDTO(TransactionGroupDTO dto) {
        this.name = dto.getMonth() + "/" + dto.getYear();
        this.income = new Value(dto.getIncome()).getFormatted();
        this.outcome = new Value(dto.getOutcome()).getFormatted();
        this.total = new Value(dto.getTotal()).getFormatted();
        this.value = new Value(dto.getValue()).getFormatted();
        this.incomeContract = new Value(dto.getIncomeContract()).getFormatted();
        this.outcomeContract = new Value(dto.getOutcomeContract()).getFormatted();
        this.start = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIncomeContract() {
        return incomeContract;
    }

    public void setIncomeContract(String incomeContract) {
        this.incomeContract = incomeContract;
    }

    public String getOutcomeContract() {
        return outcomeContract;
    }

    public void setOutcomeContract(String outcomeContract) {
        this.outcomeContract = outcomeContract;
    }
}
