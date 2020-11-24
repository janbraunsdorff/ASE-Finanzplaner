package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;
import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionService implements TransactionApplication {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public TransactionDTO createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException {
        accountRepo.getAccountByAcronym(query.getAccountAcronym());
        Transaction transaction = new Transaction(query.getAccountAcronym(), query.getValue(), query.getDate(), query.getThirdParty(), query.getCategory(), query.getContract());
        transactionRepo.createTransaction(transaction);
        return new TransactionDTO(transaction.getValue(), transaction.getDate(), transaction.getThirdParty(), transaction.getCategory(), transaction.getContract(), transaction.getId());
    }

    @Override
    public List<TransactionDTO> getTransactions(TransactionGetQuery query) {
        List<Transaction> accounts = this.transactionRepo.getTransactionOfAccount(query.getAccount(), query.getCount());
        return accounts.stream().map(a -> new TransactionDTO(a.getValue(), a.getDate(), a.getThirdParty(), a.getCategory(), a.getContract(), a.getId())).collect(Collectors.toList());
    }
}
