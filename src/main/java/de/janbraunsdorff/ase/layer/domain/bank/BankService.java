package de.janbraunsdorff.ase.layer.domain.bank;


import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
                .map(this::searchForBank)
                .collect(Collectors.toList());
    }

    private BankDTO searchForBank(Bank b) {
        int size = 0;
        int amount = 0;
        try {
            Set<String> accounts = this.accountRepo.getAccountNamesOfBankByBankAcronym(b.getAcronym());
            amount = this.transactionRepo.getValueOfAccount(LocalDate.MIN, LocalDate.MAX, accounts);
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
        this.bankRepo.deleteBankByAcronym(command.acronym());
    }


}
