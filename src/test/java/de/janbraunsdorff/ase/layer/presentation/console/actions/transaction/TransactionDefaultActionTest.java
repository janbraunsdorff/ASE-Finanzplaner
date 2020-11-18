package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.transaction.TransactionHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TransactionDefaultActionTest {
    @Test
    public void actionReturnsDefaultAction(){
        TransactionDefaultAction action = new TransactionDefaultAction();
        Result act = action.act("");

        assertThat(act, Matchers.instanceOf(TransactionHelpResult.class));
    }
}
