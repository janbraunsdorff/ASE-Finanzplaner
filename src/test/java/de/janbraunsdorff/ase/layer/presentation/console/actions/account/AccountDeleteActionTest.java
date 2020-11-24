package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AccountDeleteActionTest {
    @Test
    public void deleteAccount() throws AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountDeleteAction action = new AccountDeleteAction(app);

        Result act = action.act("account delete -a acronym");
        assertThat(act, instanceOf(AccountDeleteResult.class));
        assertThat(app.deleteCommand.getAccountAcronym(), is("acronym"));
    }

    @Test
    public void deleteAccountIncompleteStatement() throws AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountDeleteAction action = new AccountDeleteAction(app);

        assertThat(action.act("account delete -a"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account delete"), instanceOf(AccountHelpResult.class));
    }
}
