package de.janbraunsdorff.ase.layer.domain.contract.command;

import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.contract.data.Interval;

import java.time.LocalDate;

public record ContractCreateCommand(
        String name,
        String account,
        LocalDate start,
        LocalDate end,
        Value expectedValue,
        String expected,
        Interval interval,
        String thirdParty
) {

}
