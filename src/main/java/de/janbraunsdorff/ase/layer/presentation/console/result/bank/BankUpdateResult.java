package de.janbraunsdorff.ase.layer.presentation.console.result.bank;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class BankUpdateResult implements Result {

    private final BankEntity bankEntity;

    public BankUpdateResult(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Eine Bank wurde aktualisiert")
                .addInformation(String.format("ID: %s | Name: %s | Abkürzung: %s | Accounts: %d", bankEntity.getId(), bankEntity.getName(), bankEntity.getAcronym(), bankEntity.getAccounts().size()))
                .build();

    }
}
