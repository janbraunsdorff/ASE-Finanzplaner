package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class AccountDefaultActionTest {

    @Test
    public void getAccountHelp() {
        AccountDefaultAction action = new AccountDefaultAction();
        Result act = action.act("");

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }

}
