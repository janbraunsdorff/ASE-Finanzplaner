package de.janbraunsdorff.ase.layer.presentation;

import de.janbraunsdorff.ase.layer.domain.account.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.persistence.memory.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;

import java.util.List;

public interface AccountApplication {
    List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundExecption, AccountNotFoundException;
    AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundExecption, AcronymAlreadyExistsException;
    void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException;

}
