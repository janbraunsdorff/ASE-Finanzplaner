package de.janbraunsdorff.ase.layer.domain.bank;


import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.util.List;
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

    public List<BankDTO> get() {
        return this.bankRepo.getBank()
                .stream()
                .map(b -> {
                    int size = 0;
                    int amount = 0;
                    try {
                        List<Account> accounts = accountRepo.getAccountsOfBankByBankAcronym(b.getAcronym());
                        size = accounts.size();
                        amount += accounts
                                .stream()
                                .map(account -> transactionRepo.getValueOfAccount(account.getAcronym()))
                                .reduce(0, Integer::sum);
                    } catch (BankNotFoundException ignored) {
                    }
                    return new BankDTO(b.getName(), b.getAcronym(), new Value(amount), size, b.getType());
                })
                .collect(Collectors.toList());
    }

    public BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException {
        Bank bank = new Bank(command.getName(), command.getAcronym(), command.getType());

        this.bankRepo.createBank(bank);

        return new BankDTO(bank.getName(), bank.getAcronym(), new Value(0), 0, bank.getType());
    }

    public void deleteByAcronym(BankDeleteCommand command) {
        this.bankRepo.deleteBankByAcronym(command.getAcronym());
    }


}
