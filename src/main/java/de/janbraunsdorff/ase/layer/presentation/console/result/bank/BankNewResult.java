package de.janbraunsdorff.ase.layer.presentation.console.result.bank;


import de.janbraunsdorff.ase.layer.persistence.repositories.entität.BankEntity;
import de.janbraunsdorff.ase.layer.presentation.console.printer.OutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class BankNewResult implements Result {

    private final BankEntity bankEntity;

    public BankNewResult(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    @Override
    public String print() {
        return  new OutputBuilder()
                .addText("Eine neune Bank wurde angelget")
                .addNewLine()
                .addInfoText(String.format("ID: %s | Name: %s | Abkürzung: %s | Accounts: %d\n", bankEntity.getId(), bankEntity.getName(), bankEntity.getAcronym(), bankEntity.getAccounts().size()))
                .build();

    }
}
