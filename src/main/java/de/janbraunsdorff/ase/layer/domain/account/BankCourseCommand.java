package de.janbraunsdorff.ase.layer.domain.account;

public record BankCourseCommand(
        int month,
        String bankAcronym
) {
}
