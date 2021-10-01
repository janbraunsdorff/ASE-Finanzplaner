package de.janbraunsdorff.ase.layer.domain.contract.data;

import java.time.LocalDate;

import de.janbraunsdorff.ase.layer.domain.Value;

public record ContractDTO(
        String name,
        String expected,
        LocalDate start,
        LocalDate end,
        int numberOfTransactions,
        Value averageAmount,
        Value expectedAmount,
        Value totalExpense,
        Interval interval,
        String account
) {
}
