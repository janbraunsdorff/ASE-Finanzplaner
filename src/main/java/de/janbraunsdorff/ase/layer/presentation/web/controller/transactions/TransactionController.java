package de.janbraunsdorff.ase.layer.presentation.web.controller.transactions;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.domain.account.AccountAnalyticsApplication;
import de.janbraunsdorff.ase.layer.domain.account.AccountIOApplication;
import de.janbraunsdorff.ase.layer.domain.account.querry.AccountGetByAcronymQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionApplication;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGetQuery;
import de.janbraunsdorff.ase.layer.domain.transaction.TransactionGroupCommand;
import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.AllTransactionRequest;
import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.AllTransactionResponse;
import de.janbraunsdorff.ase.layer.presentation.web.controller.transactions.date.TransactionWebDTO;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionApplication transactionApplication;
    private final AccountIOApplication accountIOApplication;
    private final AccountAnalyticsApplication accountAnalyticsApplication;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public TransactionController(TransactionApplication transactionApplication, AccountIOApplication accountIOApplication, AccountAnalyticsApplication accountAnalyticsApplication) {
        this.transactionApplication = transactionApplication;
        this.accountIOApplication = accountIOApplication;
        this.accountAnalyticsApplication = accountAnalyticsApplication;
    }

    @PostMapping("account")
    public AllTransactionResponse accounts(@RequestBody AllTransactionRequest request) throws AccountNotFoundException, BankNotFoundException {
        var tranactions = transactionApplication.getTransactionsOfMultipleAccounts(new TransactionGetQuery(request.getAccount(), 150))
                .stream().map(TransactionWebDTO::new).collect(Collectors.toList());
        var account = accountIOApplication.getAccount(new AccountGetByAcronymQuery(request.getAccount()));

        var transactions = transactionApplication.groupMonthly(new TransactionGroupCommand(request.getAccount()));
        var amounts = transactions.stream().map(t -> t.getValue() / 100.0).collect(Collectors.toList());
        var labels = transactions.stream().map(t -> String.format("%2s/%4s", t.getMonth(), t.getYear())).collect(Collectors.toList());


        return new AllTransactionResponse(tranactions, account.getName(), account.getValue().getFormatted(), amounts, labels);
    }
}
