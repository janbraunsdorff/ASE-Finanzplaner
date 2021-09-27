package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.domain.Value;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionCreateCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDTO;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionDeleteCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetInIntervalQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetLastQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupCommand;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupDTO;

public class TransactionApplicationTestImplementation implements TransactionApplication {
    public TransactionCreateCommand transactionCreateCommand;
    public TransactionGetQuery transactionGetQuery;
    public TransactionDeleteCommand transactionDeleteCommand;
    public TransactionGroupCommand groupMonthly;
    public List<TransactionGetInIntervalQuery> transactionGetInIntervalQuery;

    public TransactionApplicationTestImplementation() {
        this.transactionGetInIntervalQuery = new ArrayList<>();
    }

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
        this.transactionGetInIntervalQuery.add(query);
        return Collections.singletonList(new TransactionDTO(new Value(0), LocalDate.of(2020, 1, 1), "", "", false, "", ""));
    }

    @Override
    public List<TransactionDTO> deleteTransaction(TransactionDeleteCommand command) throws TransactionNotFoundException {
        if (command.id()[0].equals("none")) {
            throw new TransactionNotFoundException("none");
        }

        this.transactionDeleteCommand = command;
        var list = new ArrayList<TransactionDTO>();
        for (String id : command.id()) {
            list.add(new TransactionDTO(null, null, "", "", false, id, ""));
        }

        return list;
    }

    @Override
    public List<TransactionGroupDTO> groupMonthly(TransactionGroupCommand command) {
        this.groupMonthly = command;
        return Collections.emptyList();
    }

    @Override
    public List<TransactionDTO> getLast(TransactionGetLastQuery transactionGetLastQuery) {
        return null;
    }
}
