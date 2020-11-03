package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.ICrudAccount;
import de.janbraunsdorff.ase.layer.persistence.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountResult;

import java.util.List;
import java.util.Map;

public class AccountAllAction implements Action {

    private final ICrudAccount service;

    public AccountAllAction(ICrudAccount service){
        this.service = service;
    }

    @Override
    public Result act(String command) {
        Map<String, String> tags = parseCommand(command, 2);
        if (areTagsPresent(tags, "-i")){
            String id = tags.get("-i");
            List<AccountEntity> accountsOfBank = service.getAccountsOfBank(id);
            return new AccountResult(accountsOfBank);
        }

        if (areTagsPresent(tags, "-a")){
            String id = tags.get("-a");
            List<AccountEntity> accountsOfBank = service.getAccountsOfBankByAcronym(id);
            return new AccountResult(accountsOfBank);
        }

        return new AccountHelpResult();
    }
}
