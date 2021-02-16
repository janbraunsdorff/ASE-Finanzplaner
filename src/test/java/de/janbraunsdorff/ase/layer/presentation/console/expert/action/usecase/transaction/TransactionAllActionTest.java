package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransactionAllActionTest {

    @Test
    public void allTransactions() throws AccountNotFoundException {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionAllAction(app);
        var command = new ExpertCommand("transaction all -a acronym", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(TransactionAllResult.class));

        assertThat(app.transactionGetQuery.account(), Matchers.is("acronym"));
        assertThat(app.transactionGetQuery.count(), Matchers.is(20));
    }

    @Test
    public void allTransactionsMissingAccount() throws AccountNotFoundException {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionAllAction(app);
        var command = new ExpertCommand("transaction all", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(TransactionHelpResult.class));
    }

    @Test
    public void allTransactionsWithCount() throws AccountNotFoundException {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionAllAction(app);
        var command = new ExpertCommand("transaction all -a acronym -n 10", 2);

        Result act = action.act(command);

        assertThat(act, Matchers.instanceOf(TransactionAllResult.class));

        assertThat(app.transactionGetQuery.account(), Matchers.is("acronym"));
        assertThat(app.transactionGetQuery.count(), Matchers.is(10));
    }

    @Test
    public void allTransactionsWithCountNotMaleFormed() {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionAllAction(app);
        var command = new ExpertCommand("transaction all -a acronym -n aa", 2);

        assertThrows(NumberFormatException.class, () -> action.act(command));
    }

    @Test
    public void allTransactionsWithId() throws AccountNotFoundException, NoSuchFieldException, IllegalAccessException {
        var app = new TransactionApplicationTestImplementation();
        var action = new TransactionAllAction(app);
        var command = new ExpertCommand("transaction all -a acronym -f", 2);

        Result act = action.act(command);

        Field f = act.getClass().getDeclaredField("withId");
        f.setAccessible(true);
        assertThat(f.get(act), Matchers.is(true));

    }
}
