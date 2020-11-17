package de.janbraunsdorff.ase.layer.presentation.console.result.bank;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Bank;
import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class BankNewResult implements Result {

    private final Bank bankEntity;

    public BankNewResult(Bank bankEntity) {
        this.bankEntity = bankEntity;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Eine neune Bank wurde angelget")
                .addInformation(String.format("ID: %s | Name: %s | Abkürzung: %s | Accounts: %d", bankEntity.getAcronym() , bankEntity.getName(), bankEntity.getAcronym(), bankEntity.getAccounts().size()))
                .build();

    }
}
