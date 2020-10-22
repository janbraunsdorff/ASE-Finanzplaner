package de.janbraunsdorff.ase.userinterface.console.actions.account;

import de.janbraunsdorff.ase.tech.repositories.entität.AccountEntity;
import de.janbraunsdorff.ase.usecases.crud.ICrudAccount;
import de.janbraunsdorff.ase.userinterface.console.actions.Action;
import de.janbraunsdorff.ase.userinterface.console.result.Result;
import de.janbraunsdorff.ase.userinterface.console.result.account.AccountResult;
import de.janbraunsdorff.ase.userinterface.console.result.bank.BankHelpResult;

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
        if (!areTagsPresent(tags, "-i")){
            return new BankHelpResult();
        }

        String id = tags.get("-i");
        List<AccountEntity> accountsOfBank = service.getAccountsOfBank(id);
        return new AccountResult(accountsOfBank);
    }
}
