package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.transaction.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionApplicationTestImplementation implements TransactionApplication {
    public TransactionCreateCommand transactionCreateCommand;
    public TransactionGetQuery transactionGetQuery;
    public TransactionDeleteCommand transactionDeleteCommand;

    @Override
    public TransactionDTO createTransactionByAccountId(TransactionCreateCommand query) throws AccountNotFoundException {
        this.transactionCreateCommand = query;
        return new TransactionDTO(null, null, "", "", false, "", "");
    }

    @Override
    public List<TransactionDTO> getTransactionsOfMultipleAccounts(TransactionGetQuery query) throws AccountNotFoundException {
        this.transactionGetQuery = query;
        return Collections.emptyList();
    }

    @Override
    public List<TransactionDTO> getTransactionsOfMultipleAccounts(TransactionGetInIntervalQuery query) throws AccountNotFoundException {
        return null;
    }

    @Override
    public List<TransactionDTO> deleteTransaction(TransactionDeleteCommand command) throws TransactionNotFoundException {
        if (command.id()[0].equals("none")){
            throw new TransactionNotFoundException("none");
        }

        this.transactionDeleteCommand = command;
        var  list = new ArrayList<TransactionDTO>();
        for (String id : command.id()) {
            list.add(new TransactionDTO(null,null,"", "", false, id, ""));
        }

        return list;
    }

    @Override
    public List<TransactionGroupDTO> groupMonthly(TransactionGroupCommand command) {
        return null;
    }

    @Override
    public List<TransactionDTO> getLast(TransactionGetLastQuery transactionGetLastQuery) {
        return null;
    }
}
