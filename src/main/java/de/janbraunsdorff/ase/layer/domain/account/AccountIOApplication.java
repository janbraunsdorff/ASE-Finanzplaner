package de.janbraunsdorff.ase.layer.domain.account;

import java.util.List;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.command.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.account.data.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetQuery;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountsGetByAcronymQuery;

public interface AccountIOApplication {
    List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException, AccountNotFoundException;
    List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException;
    AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundException, AcronymAlreadyExistsException;
    AccountDTO getAccount(AccountGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException;
    void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException;
}
