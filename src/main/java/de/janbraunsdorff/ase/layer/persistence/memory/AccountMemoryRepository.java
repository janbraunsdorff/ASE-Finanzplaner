package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountMemoryRepository implements AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public void createAccount(Account account) throws AcronymAlreadyExistsException {
        if (accounts.containsKey(account.getAcronym())) {
            throw new AcronymAlreadyExistsException(account.getAcronym());
        }
        this.accounts.put(account.getAcronym(), account);
    }

    @Override
    public Account getAccountByAcronym(String acronym) throws AccountNotFoundException {
        if (this.accounts.containsKey(acronym)) {
            return this.accounts.get(acronym);
        }

        throw new AccountNotFoundException(acronym);
    }

    @Override
    public List<Account> getAccountsOfBankByBankAcronym(String acronym) {
        return this.accounts.values()
                .stream()
                .filter(a -> a.getBankAcronym().equals(acronym))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountByAcronym(String acronym) {
        this.accounts.remove(acronym);
    }

}
