package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class BankDefaultActionTest {
    @Test
    public void actionReturnsDefaultAction(){
        BankDefaultAction bankDefaultAction = new BankDefaultAction();
        Result act = bankDefaultAction.act("");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
