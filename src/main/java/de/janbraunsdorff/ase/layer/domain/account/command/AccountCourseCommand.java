package de.janbraunsdorff.ase.layer.domain.account.command;

public record AccountCourseCommand(
        int month,
        String... accountAcronym
) {
}
