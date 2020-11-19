package de.janbraunsdorff.ase.layer.domain.account;


import de.janbraunsdorff.ase.layer.domain.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.TransactionRepository;
import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.persistence.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.persistence.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.AccountApplication;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService implements AccountApplication {
    private final AccountRepository repo;
    private final TransactionRepository transactionRepo;

    public AccountService(AccountRepository repo, TransactionRepository transactionRepo) {
        this.repo = repo;
        this.transactionRepo = transactionRepo;
    }

    public List<AccountDTO> getAccountsOfBank(AccountGetQuery query) throws BankNotFoundExecption {
        List<Account> accounts = this.repo.getAccountsOfBankByBankAcronym(query.getId());
        return accounts.stream().map(a -> {
            int amount = 0;
            try {
                amount = transactionRepo.getTransactionOfAccount(a.getAcronym(), -1).size();
            } catch (TransactionNotFoundException e) {
                e.printStackTrace();
            }
            return new AccountDTO(a.getName(), a.getNumber(), amount, a.getAcronym(), transactionRepo.getValueOfAccount(a.getAcronym()));

        }).collect(Collectors.toList());
    }

    public AccountDTO createAccountByAcronym(AccountCreateCommand command) throws AcronymAlreadyExistsException {
        Account account = new Account(command.getBank(), command.getName(), command.getNumber(), command.getAcronym());
        this.repo.createAccount(account);
        return new AccountDTO(account.getName(), account.getNumber(), 0, account.getAcronym(), 0);
    }

    public void deleteByAcronym(AccountDeleteCommand command) throws AccountNotFoundException {
        this.repo.deleteAccountByAcronym(command.getAccountAcronym());
    }
}
