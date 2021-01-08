package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionService implements TransactionApplication {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public TransactionDTO createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException {
        checkIfAccountExists(query.accountAcronym());
        Transaction transaction = new Transaction(query.accountAcronym(), query.value(), query.date(), query.thirdParty(), query.category(), query.isContract());
        transactionRepo.createTransaction(transaction);
        return new TransactionDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsOfMultipleAccounts(TransactionGetQuery query) throws AccountNotFoundException {
        checkIfAccountExists(query.account());
        List<Transaction> accounts = this.transactionRepo.getTransactionOfAccount(query.account(), query.count());
        return accounts.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionsOfMultipleAccounts(TransactionGetInIntervalQuery query) throws AccountNotFoundException {
        for (String s : query.account()){
            checkIfAccountExists(s);
        }
        List<Transaction> accounts = this.transactionRepo.getTransactionOfAccount(query.account(), query.start(), query.end());
        return accounts.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> deleteTransaction(String... id) throws TransactionNotFoundException {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (String s : id) {
            Optional<Transaction> transaction = this.transactionRepo.deleteTransactionById(s);
            transaction.ifPresent(value -> transactionDTOS.add(new TransactionDTO(value)));
        }
        return transactionDTOS;
    }

    private void checkIfAccountExists(String s) throws AccountNotFoundException {
        accountRepo.getAccountByAcronym(s);
    }
}
