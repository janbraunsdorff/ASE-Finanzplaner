package de.janbraunsdorff.ase.layer.domain;

import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountTestRepo implements AccountRepository {
    public String acronymDelete;
    @Override
    public void createAccount(Account account) throws AcronymAlreadyExistsException {

    }

    @Override
    public Account getAccountByAcronym(String acronym) throws AccountNotFoundException {
        if(acronym.equals("accountAcronym")){
            return new Account("bankAcronym", "name", "number", "ac");
        }
        throw new AccountNotFoundException(acronym);
    }

    @Override
    public List<Account> getAccountsOfBankByBankAcronym(String bank) throws BankNotFoundException {
        if (!bank.equals("b1")){
            return new ArrayList<>();
        }
        return new ArrayList<Account>(){{
            add(new Account("b1", "name1", "number1", "accountAcronym"));
            add(new Account("b1", "name2", "number2", "a2"));
            add(new Account("b1", "name3", "number3", "a3"));
            add(new Account("b1", "name4", "number4", "a4"));
        }};
    }

    @Override
    public void deleteAccountByAcronym(String acronym) throws AccountNotFoundException {
        this.acronymDelete = acronym;
    }
}
