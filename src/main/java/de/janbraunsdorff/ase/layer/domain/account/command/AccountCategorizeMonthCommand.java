package de.janbraunsdorff.ase.layer.domain.account.command;

import de.janbraunsdorff.ase.layer.domain.bank.BankType;

import java.time.LocalDate;

public record AccountCategorizeMonthCommand(
        LocalDate month
) {
}
