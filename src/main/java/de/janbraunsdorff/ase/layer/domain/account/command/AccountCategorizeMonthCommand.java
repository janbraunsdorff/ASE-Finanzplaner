package de.janbraunsdorff.ase.layer.domain.account.command;

import java.time.LocalDate;

public record AccountCategorizeMonthCommand(
        LocalDate month
) {
}
