package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class AccountAllActionTest {
    @Test
    public void getAllAccountsOfBank() throws BankNotFoundException, AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAllAction action = new AccountAllAction(app);

        Result act = action.act(new ExpertCommand("account get -a acronym", 2));
        assertThat(act, Matchers.instanceOf(AccountAllResult.class));
        assertThat(app.getQuery.getId(), Matchers.is("acronym"));
    }

    @Test
    public void getAllAccountsOfBankIncompleteStatement() throws BankNotFoundException, AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAllAction action = new AccountAllAction(app);

        assertThat(action.act(new ExpertCommand("account get -a", 2)), Matchers.instanceOf(AccountHelpResult.class));
        assertThat(action.act(new ExpertCommand("account get", 2)), Matchers.instanceOf(AccountHelpResult.class));
    }
}
