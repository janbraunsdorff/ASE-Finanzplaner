package de.janbraunsdorff.ase.layer.domain.bank;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.account.data.Account;
import de.janbraunsdorff.ase.layer.domain.transaction.Transaction;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

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
                .map(this::searchForBank)
                .collect(Collectors.toList());
    }

    private BankDTO searchForBank(Bank b) {
        int size = 0;
        int amount = 0;
        try {
            Set<String> accounts = this.accountRepo.getAccountIdsOfBankByBankAcronym(b.getAcronym());
            amount = this.transactionRepo.getValueOfAccount(LocalDate.of(0,1,1), LocalDate.now(), accounts).intValue();
            size = accounts.size();
        } catch (BankNotFoundException ignored) {
        }
        return new BankDTO(b.getName(), b.getAcronym(), new Value(amount), size, b.getType());
    }

    public BankDTO create(BankCreateCommand command) throws AcronymAlreadyExistsException {
        Bank bank = new Bank(command.name(), command.acronym(), BankType.getByName(command.type()));
        this.bankRepo.createBank(bank);
        return new BankDTO(bank.getName(), bank.getAcronym(), new Value(0), 0, bank.getType());
    }

    public void deleteByAcronym(BankDeleteCommand command) {
        try {
            this.accountRepo.getAccountsOfBankByBankAcronym(command.acronym())
                    .stream()
                    .map(Account::getAcronym)
                    .forEach(ac -> {
                        try {
                            for (Transaction t : this.transactionRepo.getTransactionOfAccount(ac, -1)) {
                                this.transactionRepo.deleteTransactionById(t.getId());
                            }
                            this.accountRepo.deleteAccountByAcronym(ac);
                        } catch (Exception ignore) {
                        }
                    });
        }catch (BankNotFoundException ignore){

        }
        this.bankRepo.deleteBankByAcronym(command.acronym());
    }


}
