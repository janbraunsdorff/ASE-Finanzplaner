package de.janbraunsdorff.ase.layer.domain.bank;

import de.janbraunsdorff.ase.layer.domain.Value;

public record BankDTO(
        String name,
        String acronym,
        Value value,
        Integer numberOfAccount,
        BankType type) {

}
