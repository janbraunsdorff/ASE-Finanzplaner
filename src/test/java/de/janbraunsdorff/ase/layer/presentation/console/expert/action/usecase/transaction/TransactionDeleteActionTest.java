package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.system.ErrorResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;


class TransactionDeleteActionTest {
    @Test
    public void deleteTransaction() throws Exception {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionDeleteAction(app);
        var command = new ExpertCommand("transaction delete -id 123", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(TransactionDeleteResult.class));

        assertThat(Arrays.stream(app.transactionDeleteCommand.id()).collect(Collectors.toList()), Matchers.contains("123"));
    }

    @Test
    public void deleteMultipleTransactions() throws Exception {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionDeleteAction(app);
        var command = new ExpertCommand("transaction delete -id 123 456", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(TransactionDeleteResult.class));

        assertThat(Arrays.stream(app.transactionDeleteCommand.id()).collect(Collectors.toList()), Matchers.contains("123", "456"));
    }

    @Test
    public void deleteTransactionsMissingId() throws Exception {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionDeleteAction(app);
        var command = new ExpertCommand("transaction delete", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(TransactionHelpResult.class));
    }

    @Test
    public void deleteTransactionsTestResponseId() throws Exception {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionDeleteAction(app);
        var command = new ExpertCommand("transaction delete -id 123", 2);

        Result act = action.act(command);
        var res = (TransactionDeleteResult) act;

        assertThat(res.id(), Matchers.is("123"));
    }

    @Test
    public void deleteMultipleTransactionsTestResponseId() throws Exception {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionDeleteAction(app);
        var command = new ExpertCommand("transaction delete -id 123 345", 2);

        Result act = action.act(command);
        var res = (TransactionDeleteResult) act;

        assertThat(res.id(), Matchers.is("123 | 345"));
    }

    @Test
    public void deleteNoneExisting() throws Exception {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionDeleteAction(app);
        var command = new ExpertCommand("transaction delete -id none", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(ErrorResult.class));
    }
}
