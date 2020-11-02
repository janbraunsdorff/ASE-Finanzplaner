package de.janbraunsdorff.ase.userinterface.console.result.account;

import de.janbraunsdorff.ase.tech.printer.OutputBuilder;
import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.userinterface.console.result.TypedResult;

public class AccountNewResult implements TypedResult<AccountEntity> {

    private final AccountEntity account;

    public AccountNewResult(AccountEntity account){
        this.account = account;
    }

    @Override
    public String print() {
        return  new OutputBuilder()
                .addText("Ein neues Konto wurde eröffnet")
                .addNewLine()
                .addInfoText(String.format("ID: %s | Name: %s | Abkürzung: %s | Nummer: %s | Accounts: %d\n", account.getId(), account.getName(), account.getAcronym(), account.getNumber(), account.getTransactions().size()))
                .build();
    }
}
