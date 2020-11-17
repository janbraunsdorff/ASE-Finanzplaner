package de.janbraunsdorff.ase.layer.presentation.console.result.account;


import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.TypedResult;

public class AccountNewResult implements TypedResult<AccountMemoryEntity> {

    private final Account account;

    public AccountNewResult(Account account) {
        this.account = account;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Ein Account wurde erstellt")
                .addInformation(String.format("ID: %s | Name: %s | Abkürzung: %s | Nummer: %s", account.getId(), account.getName(), account.getAcronym(), account.getNumber()))
                .build();
    }
}
