package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AccountDeleteActionTest {
    @Test
    public void deleteAccount() throws AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountDeleteAction action = new AccountDeleteAction(app);

        Result act = action.act(new ExpertCommand("account delete -a acronym", 2));
        assertThat(act, instanceOf(AccountDeleteResult.class));
        assertThat(app.deleteCommand.getAccountAcronym(), is("acronym"));
    }

    @Test
    public void deleteAccountIncompleteStatement() throws AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountDeleteAction action = new AccountDeleteAction(app);

        assertThat(action.act(new ExpertCommand("account delete -a", 2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new ExpertCommand("account delete", 2)), instanceOf(AccountHelpResult.class));
    }
}
