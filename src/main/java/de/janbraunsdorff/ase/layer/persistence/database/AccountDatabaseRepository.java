package de.janbraunsdorff.ase.layer.persistence.database;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AccountDatabaseRepository  implements AccountRepository {

    private final AccountSpringRepository repo;

    public AccountDatabaseRepository(AccountSpringRepository repo){
        this.repo = repo;
    }

    @Override
    @Transactional
    public void createAccount(Account account) throws AcronymAlreadyExistsException {
        var entity = new AccountDatabaseEntity(account.getId(), account.getBankAcronym(), account.getName(), account.getNumber(), account.getAcronym());
        this.repo.save(entity);
    }

    @Override
    @Transactional
    public Account getAccountByAcronym(String acronym) throws AccountNotFoundException {
        return this.repo.findByAcronym(acronym).toDomain();
    }

    @Override
    @Transactional
    public List<Account> getAccountsOfBankByBankAcronym(String bank) throws BankNotFoundException {
        return this.repo.findByBankAcronymLike(bank).stream().map(AccountDatabaseEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Set<String> getAccountNamesOfBankByBankAcronym(String bank) throws BankNotFoundException {
        return this.getAccountsOfBankByBankAcronym(bank).stream().map(Account::getAcronym).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteAccountByAcronym(String acronym) throws AccountNotFoundException {
        this.repo.delete(this.repo.findByAcronym(acronym));
    }

    @Override
    @Transactional
    public List<Account> getAll() {
        return StreamSupport.stream(this.repo.findAll().spliterator(), false).map(AccountDatabaseEntity::toDomain).collect(Collectors.toList());
    }
}
