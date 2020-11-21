package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankDefaultActionTest {
    @Test
    public void bankDefaultReturnsBankHelp(){
        BankDefaultAction action = new BankDefaultAction();
        Result act = action.act("");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
