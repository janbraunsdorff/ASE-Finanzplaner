package de.janbraunsdorff.ase.layer.domain.account.command;

public record BankCourseCommand(
        int month,
        String bankAcronym
) {
}
