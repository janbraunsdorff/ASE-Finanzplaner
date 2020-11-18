package de.janbraunsdorff.ase.layer.domain.bank;


import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.repository.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.repository.BankRepository;
import de.janbraunsdorff.ase.layer.domain.repository.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.domain.repository.exceptions.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.presentation.BankApplication;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BankService implements BankApplication {

    private final BankRepository bankRepo;
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;

    public BankService(BankRepository repo, AccountRepository accountRepo, TransactionRepository transactionRepo) {
        this.bankRepo = repo;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    public List<BankDTO> get() throws BankNotFoundExecption {
        return this.bankRepo.getBank()
                .stream()
                .map(b -> {
                    int size = 0;
                    AtomicInteger amount = new AtomicInteger();
                    try {
                        List<Account> accounts = accountRepo.getAccountsOfBankByBankId(b.getId());
                        accounts.forEach(a -> amount.addAndGet(transactionRepo.getValueOfAccount(a.getId())));
                    } catch (BankNotFoundExecption ex) {
                        ex.printStackTrace();
                    }
                    return new BankDTO(b.getName(), b.getAcronym(), amount.get(), size);
                })
                .collect(Collectors.toList());
    }

    public BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException {
        Bank bank = new Bank(command.getName(), command.getAcronym());

        this.bankRepo.createBank(bank);

        return new BankDTO(bank.getName(), bank.getAcronym(), 0, 0);
    }

    public void deleteByAcronym(BankDeleteCommand command) {
        this.bankRepo.deleteBankByAcronym(command.getAcronym());
    }
}
