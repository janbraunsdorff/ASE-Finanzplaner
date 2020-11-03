package de.janbraunsdorff.ase.layer.presentation.console.result.account;

import de.janbraunsdorff.ase.layer.presentation.console.printer.InformationOutputBuilder;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public class AccountDeleteResult implements Result {
    private final String id;

    public AccountDeleteResult(String id) {
        this.id = id;
    }

    @Override
    public String print() {
        return new InformationOutputBuilder()
                .addHeadline("Ein Account wurde gelöscht")
                .addInformation(String.format("Account mit der Kennung: %s wurde gelöscht", this.id))
                .build();
    }
}
