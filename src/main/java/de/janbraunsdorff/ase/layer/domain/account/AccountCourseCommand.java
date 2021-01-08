package de.janbraunsdorff.ase.layer.domain.account;

public record AccountCourseCommand(
        int month,
        String... accountAcronym
) {
}
