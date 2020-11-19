package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.TransactionRepository;
import de.janbraunsdorff.ase.layer.domain.account.Account;
import de.janbraunsdorff.ase.layer.domain.AccountRepository;
import de.janbraunsdorff.ase.layer.persistence.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.persistence.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.TransactionApplication;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionService implements TransactionApplication {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public void createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException {
        Account account = this.accountRepo.getAccountByAcronym(query.getAccountAcronym());
        Transaction transaction = new Transaction(account.getId(), query.getValue(), query.getDate(), query.getThirdParty(), query.getCategory(), query.getContract());
        transactionRepo.createTransaction(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactions(TransactionGetQuery query) throws TransactionNotFoundException {
        List<Transaction> accounts = this.transactionRepo.getTransactionOfAccount(query.getAccount(), query.getCount());
        return accounts.stream().map(a -> new TransactionDTO(a.getValue(), a.getDate(), a.getThirdParty(), a.getCategory(), a.getContract(), a.getId())).collect(Collectors.toList());
    }
}
