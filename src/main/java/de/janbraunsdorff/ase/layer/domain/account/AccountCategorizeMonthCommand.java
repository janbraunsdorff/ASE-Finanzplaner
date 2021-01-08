package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.bank.BankType;

import java.time.LocalDate;

public record AccountCategorizeMonthCommand(
        LocalDate month
) {
}
