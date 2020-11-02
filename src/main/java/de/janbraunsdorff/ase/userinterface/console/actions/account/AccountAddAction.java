package de.janbraunsdorff.ase.userinterface.console.actions.account;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudAccount;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountNewResult;

import java.util.Collections;
import java.util.Map;

public class AccountAddAction implements Action {

    private final ICrudAccount service;

    public AccountAddAction(ICrudAccount service) {
        this.service = service;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsPresent(tags, "-na", "-nr", "-ac")){
            return new AccountHelpResult();
        }

        AccountEntity account = new AccountEntity(tags.get("-na"), tags.get("-nr"), tags.get("-ac"), 0, Collections.emptyList());

        if (areTagsPresent(tags, "-i")){
            String id = tags.get("-i");
            account = this.service.createAccount(id, account);
            return new AccountNewResult(account);
        }

        if (areTagsPresent(tags, "-a")){
            String acronym = tags.get("-a");
            account = this.service.createAccountByAcronym(acronym, account);
            return new AccountNewResult(account);
        }

        return new AccountHelpResult();

    }
}
