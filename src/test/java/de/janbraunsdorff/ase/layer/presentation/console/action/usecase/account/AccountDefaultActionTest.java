package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.presentation.console.Command;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class AccountDefaultActionTest {

    @Test
    public void getAccountHelp() {
        AccountHelpAction action = new AccountHelpAction();
        Result act = action.act(new Command("", 0));

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }

}
