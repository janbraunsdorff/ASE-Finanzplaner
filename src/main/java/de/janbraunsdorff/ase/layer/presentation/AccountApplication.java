package de.janbraunsdorff.ase.layer.presentation;

import de.janbraunsdorff.ase.layer.domain.account.AccountCreateCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountDTO;
import de.janbraunsdorff.ase.layer.domain.account.AccountDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.account.AccountGetQuery;
import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundException;

import java.util.List;

public interface AccountApplication {
    List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException, AccountNotFoundException;
    AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundException, AcronymAlreadyExistsException;
    void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException;

}
