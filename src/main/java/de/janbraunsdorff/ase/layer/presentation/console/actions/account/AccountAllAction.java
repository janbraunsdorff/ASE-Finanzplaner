package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountResult;

import java.util.List;
import java.util.Map;

public class AccountAllAction implements Action {

    private final ICrudAccount service;

    public AccountAllAction(ICrudAccount service) {
        this.service = service;
    }

    @Override
    public Result act(String command) throws BankNotFoundExecption {
        Map<String, String> tags = parseCommand(command, 2);
        if (!areTagsAndValuesPresent(tags, "-a")) {
            return new AccountHelpResult();
        }

        String acronym = tags.get("-a");
        List<Account> get = service.getAccountsOfBankByAcronym(acronym);
        return new AccountResult(get);
    }
}
