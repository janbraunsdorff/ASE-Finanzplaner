package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransactionAllActionTest {
    @Test
    public void getAllTransaction() throws AccountNotFoundException {
        TransactionTestApplication app = new TransactionTestApplication();
        TransactionAllAction action = new TransactionAllAction(app);

        Result act = action.act(new Command("transaction all -a acronym",2));
        assertThat(act, Matchers.instanceOf(TransactionAllResult.class));
        assertThat(app.getQuery.getAccount(), Matchers.is("acronym"));
        assertThat(app.getQuery.getCount(), Matchers.is(20));

    }

    @Test
    public void getAllTransactionWithCount() throws AccountNotFoundException {
        TransactionTestApplication app = new TransactionTestApplication();
        TransactionAllAction action = new TransactionAllAction(app);

        Result act = action.act(new Command("transaction all -a acronym -n 40",2));
        assertThat(act, Matchers.instanceOf(TransactionAllResult.class));
        assertThat(app.getQuery.getAccount(), Matchers.is("acronym"));
        assertThat(app.getQuery.getCount(), Matchers.is(40));
    }

    @Test
    public void getAllTransactionWithCountAsString() throws AccountNotFoundException {
        TransactionTestApplication app = new TransactionTestApplication();
        TransactionAllAction action = new TransactionAllAction(app);

        assertThrows(NumberFormatException.class, () ->action.act(new Command("transaction all -a acronym -n number",2)));
    }

    @Test
    public void getAllTransactionWithIncompleteStatement() throws AccountNotFoundException {
        TransactionTestApplication app = new TransactionTestApplication();
        TransactionAllAction action = new TransactionAllAction(app);

        assertThat(action.act(new Command("transaction all -a",2)), Matchers.instanceOf(TransactionHelpResult.class));
        assertThat(action.act(new Command("transaction all",2)), Matchers.instanceOf(TransactionHelpResult.class));
    }
}
