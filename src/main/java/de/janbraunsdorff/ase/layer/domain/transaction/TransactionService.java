package de.janbraunsdorff.ase.layer.domain.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountRepository;

import java.time.LocalDate;
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
    public List<TransactionDTO> deleteTransaction(TransactionDeleteCommand command) throws TransactionNotFoundException {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (String s : command.id()) {
            Optional<Transaction> transaction = this.transactionRepo.deleteTransactionById(s);
            transaction.ifPresent(value -> transactionDTOS.add(new TransactionDTO(value)));
        }
        return transactionDTOS;
    }

    @Override
    public List<TransactionGroupDTO> groupMonthly(TransactionGroupCommand command) {

        List<Transaction> transactionOfAccount = this.transactionRepo.getTransactionOfAccount(command.getAccount(), -1);

        List<TransactionGroupDTO> dtos = new ArrayList<>();

        TransactionGroupDTO dto = null;

        for (Transaction t : transactionOfAccount){
            if(dto == null || (t.getDate().getMonthValue() != dto.getMonth() || t.getDate().getYear() != dto.getYear())){
                if (dto != null){
                    dtos.add(dto);
                }
                dto = new TransactionGroupDTO(command.getAccount(), t.getDate().getMonthValue(), t.getDate().getYear());
            }

            if (t.getValue() > 0){
                dto.increment(t.getValue());
                if (t.getContract()){
                    dto.addIncomeContract(t.getValue());
                }
            }else {
                dto.decrement(t.getValue());
                if (t.getContract()){
                    dto.addOutcomeContract(t.getValue());
                }
            }
        }

        if (dto != null){
            dtos.add(dto);
        }

        int totalValue = 0;
        for(int i = dtos.size()-1; i >=0; i--){
            totalValue += dtos.get(i).getTotal();
            dtos.get(i).setValue(totalValue);
        }


        return dtos;
    }

    @Override
    public List<TransactionDTO> getLast(TransactionGetLastQuery transactionGetLastQuery) {
        return this.transactionRepo.getLastTransactions(transactionGetLastQuery.getNumber())
                .stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    private void checkIfAccountExists(String s) throws AccountNotFoundException {
        accountRepo.getAccountByAcronym(s);
    }
}
