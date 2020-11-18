package de.janbraunsdorff.ase.layer.persistence.repositories.memory;

import de.janbraunsdorff.ase.layer.domain.crud.entitties.Account;
import de.janbraunsdorff.ase.layer.domain.crud.repository.CrudAccountRepository;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.AccountMemoryEntity;
import de.janbraunsdorff.ase.layer.persistence.repositories.memory.entität.BankMemoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountMemoryRepository implements CrudAccountRepository {
    private final MemoryRepository repo;

    public AccountMemoryRepository(MemoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Account createAccountByBankId(String bankId, Account entity) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        if (!this.repo.memory.containsKey(bankId)) {
            throw new BankNotFoundExecption(bankId);
        }

        Optional<AccountMemoryEntity> first = this.repo.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .filter(a -> a.getAcronym().equals(entity.getAcronym()))
                .findFirst();

        if (first.isPresent()) {
            throw new AcronymAlreadyExistsException(entity.getAcronym());
        }

        this.repo.memory.get(bankId).addAccount(new AccountMemoryEntity(entity));
        return entity;
    }

    @Override
    public Account createAccountByBankAcronym(String acronym, Account account) throws BankNotFoundExecption, AcronymAlreadyExistsException {
        Optional<BankMemoryEntity> first = this.repo.memory.values().stream().filter(b -> b.getAcronym().equals(acronym)).findFirst();

        if (!first.isPresent()){
            throw new BankNotFoundExecption(acronym);
        }

        Optional<AccountMemoryEntity> opAccount = this.repo.memory.values()
                .stream()
                .flatMap(a -> a.getAccounts().stream())
                .filter(a -> a.getAcronym().equals(account.getAcronym()))
                .findFirst();

        if (opAccount.isPresent()) {
            throw new AcronymAlreadyExistsException(account.getAcronym());
        }

        first.get().addAccount(new AccountMemoryEntity(account));
        return account;
    }

    @Override
    public List<Account> getAccountsOfBankByBankId(String bankId) throws BankNotFoundExecption {
        if (!this.repo.memory.containsKey(bankId)) {
            throw new BankNotFoundExecption(bankId);
        }
        return this.repo.memory.get(bankId).getAccounts().stream().map(AccountMemoryEntity::convertToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Account> getAccountsOfBankByBankAcronym(String bankAcronym) throws BankNotFoundExecption {
        Optional<BankMemoryEntity> bank = this.repo.memory.values().stream().filter(s -> s.getAcronym().equals(bankAcronym)).findFirst();
        if (!bank.isPresent()) {
            throw new BankNotFoundExecption(bankAcronym);
        }

        return bank.get().getAccounts().stream().map(AccountMemoryEntity::convertToDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountByAcronym(String acronym) throws AccountNotFoundException {
        deleteAccount(a -> a.getAcronym().equals(acronym), acronym);
    }

    @Override
    public void deleteAccountById(String id) throws AccountNotFoundException {
        deleteAccount(a -> a.getId().equals(id), id);
    }

    private void deleteAccount(Predicate<AccountMemoryEntity> predicate, String key) throws AccountNotFoundException {
        AtomicBoolean found = new AtomicBoolean(false);
        this.repo.memory.values().forEach(bank -> {
            Optional<AccountMemoryEntity> first = bank.getAccounts()
                    .stream()
                    .filter(predicate)
                    .findFirst();


            first.ifPresent(e -> {
                bank.removeAccount(e.getId());
                found.set(true);
            });
        });

        if (!found.get()) {
            throw new AccountNotFoundException(key);
        }
    }
}
