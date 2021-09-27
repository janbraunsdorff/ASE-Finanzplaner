package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class TransactionHelpActionTest {
    @Test
    public void createTransactionHelp(){
        var action = new TransactionHelpAction();
        var res =  action.act(null);

        assertThat(res, Matchers.instanceOf(TransactionHelpResult.class));
    }
}
