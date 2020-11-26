package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class TransactionHelpActionTest {
    @Test
    public void getTransactionHelp(){
        TransactionHelpAction action = new TransactionHelpAction();
        Result act = action.act(new Command("",0));
        assertThat(act, Matchers.instanceOf(TransactionHelpResult.class));
    }
}
