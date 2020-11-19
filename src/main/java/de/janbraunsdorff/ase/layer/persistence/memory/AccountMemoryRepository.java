package de.janbraunsdorff.ase.layer.persistence.memory;

import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountMemoryRepository implements AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public void createAccount(Account account) {
        this.accounts.put(account.getId(), account);
    }

    @Override
    public Account getAccountByAcronym(String acronym) throws AccountNotFoundException {
        Optional<Account> first = accounts.values().stream().filter(a -> a.getAcronym().equals(acronym)).findFirst();
        if (first.isPresent()){
            return first.get();
        }

        throw new AccountNotFoundException(acronym);
    }

    @Override
    public List<Account> getAccountsOfBankByBankId(String bank) {
        return this.accounts.values().stream().filter(a -> a.getBankId().equals(bank)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountByAcronym(String acronym) {
        Account acc = this.accounts.values().stream().filter(a -> a.getAcronym().equals(acronym)).findFirst().get();
        this.accounts.remove(acc.getId());
    }

    @Override
    public void deleteAccountById(String id) {
        this.accounts.remove(id);
    }
}
