package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class AccountDefaultActionTest {
    @Test
    public void defaultActionAccount(){
        AccountDefaultAction action = new AccountDefaultAction();
        Result act = action.act("");

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }
}
