package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class AccountAllActionTest {
    @Test
    public void getAllAccountsOfBank() throws BankNotFoundException, AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAllAction action = new AccountAllAction(app);

        Result act = action.act("account get -a acronym");
        assertThat(act, Matchers.instanceOf(AccountResult.class));
        assertThat(app.getQuery.getId(), Matchers.is("acronym"));
    }

    @Test
    public void getAllAccountsOfBankIncompleteStatement() throws BankNotFoundException, AccountNotFoundException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAllAction action = new AccountAllAction(app);

        assertThat(action.act("account get -a"), Matchers.instanceOf(AccountHelpResult.class));
        assertThat(action.act("account get"), Matchers.instanceOf(AccountHelpResult.class));
    }
}
