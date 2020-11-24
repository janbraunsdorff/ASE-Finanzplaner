package de.janbraunsdorff.ase.layer.presentation.console.result.bank;

import de.janbraunsdorff.ase.layer.domain.bank.BankDTO;
import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class BankNewResult implements Result {

    private final BankDTO bankEntity;

    public BankNewResult(BankDTO bankEntity) {
        this.bankEntity = bankEntity;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Eine neune Bank wurde angelget")
                .addInformation(String.format("Name: %s | Abkürzung: %s", bankEntity.getName(), bankEntity.getAcronym()))
                .build();

    }
}
