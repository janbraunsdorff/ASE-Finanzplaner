package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class TransactionAllActionTest {
    @Test
    public void getAllTransactionsWithFullAcronym() throws AccountNotFoundException {
        ICrudTransactionTestImpl service = new ICrudTransactionTestImpl();

        TransactionAllAction action = new TransactionAllAction(service);
        Result act = action.act("transaction all -a ac");

        assertThat(act, Matchers.instanceOf(TransactionResult.class));
        assertThat(service.getAllAcronym, is("ac"));
    }

    @Test
    public void getAllTransactionsTagIsMissing() throws AccountNotFoundException {
        ICrudTransactionTestImpl service = new ICrudTransactionTestImpl();

        TransactionAllAction action = new TransactionAllAction(service);
        Result act = action.act("transaction all");

        assertThat(act, instanceOf(TransactionHelpResult.class));
    }

    @Test
    public void getAllTransactionsValueIsMissing() throws AccountNotFoundException {
        ICrudTransactionTestImpl service = new ICrudTransactionTestImpl();

        TransactionAllAction action = new TransactionAllAction(service);
        Result act = action.act("transaction all -a");

        assertThat(act, instanceOf(TransactionHelpResult.class));
    }
}
