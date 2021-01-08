package de.janbraunsdorff.ase.layer.domain.account;

import de.janbraunsdorff.ase.layer.domain.*;

import java.util.HashMap;
import java.util.List;

public interface AccountApplication {
    List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundException, AccountNotFoundException;

    AccountDTO createAccountByAcronym(AccountCreateCommand command) throws BankNotFoundException, AcronymAlreadyExistsException;

    List<AccountDTO> getAccount(AccountsGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException;
    AccountDTO getAccount(AccountGetByAcronymQuery query) throws AccountNotFoundException, BankNotFoundException;

    void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException, TransactionNotFoundException;

    List<Value> getCourse(AccountCourseCommand command);

    List<Value> getCourse(BankCourseCommand command) throws BankNotFoundException;

    AccountMonthDTO getMonth(AccountCategorizeMonthCommand command);

    HashMap<String, String> getAcronymToNameMapping();
}
