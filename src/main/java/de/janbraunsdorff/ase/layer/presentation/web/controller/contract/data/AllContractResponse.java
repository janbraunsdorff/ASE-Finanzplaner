package de.janbraunsdorff.ase.layer.presentation.web.controller.contract.data;

import java.util.List;

public class AllContractResponse {
    public final List<ContractWebDTO> income;
    public final String totalIncome;
    public final List<ContractWebDTO> expense;
    public final String totalExpense;

    public AllContractResponse(List<ContractWebDTO> income, String totalIncome, List<ContractWebDTO> expense, String totalExpense) {
        this.income = income;
        this.totalIncome = totalIncome;
        this.expense = expense;
        this.totalExpense = totalExpense;
    }

    public List<ContractWebDTO> getIncome() {
        return income;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public List<ContractWebDTO> getExpense() {
        return expense;
    }

    public String getTotalExpense() {
        return totalExpense;
    }
}
