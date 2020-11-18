package de.janbraunsdorff.ase.layer.presentation.console.result.account;


import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class AccountNewResult implements Result {

    private final AccountDTO account;

    public AccountNewResult(AccountDTO account) {
        this.account = account;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Ein Account wurde erstellt")
                .addInformation(String.format("ID: %s | Name: %s | Abkürzung: %s", account.getAcronym(), account.getName(), account.getAcronym()))
                .build();
    }
}
