package de.janbraunsdorff.ase.layer.domain.contract.data;

import java.time.LocalDate;

import de.janbraunsdorff.ase.layer.domain.Value;

public record ContractDTO(
        String name,
        LocalDate start,
        LocalDate end,
        int numberOfTransactions,
        Value averageAmount,
        Value expectedAmount,
        Value totalExpense
) {
}
