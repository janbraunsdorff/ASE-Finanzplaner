package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.Value;

public record AccountMonthDTO (
         Value totalIncome,
         Value totalIncomeSalary,
         Value totalIncomeContract,
         Value totalIncomeOthers,

         Value totalExpenses,
         Value totalExpensesMonthly,
         Value totalExpensesContract,
         Value totalExpensesPurchase,
         Value totalExpensesOthers,

         Value profit,
         String percent
) {
}
