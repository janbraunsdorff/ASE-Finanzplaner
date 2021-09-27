package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.TransactionNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class AccountDeleteActionTest {
    public AccountIOApplicationTestImplementation app;
    public AccountDeleteAction action;

    @BeforeEach
    public void init() {
        this.app = new AccountIOApplicationTestImplementation();
        this.action = new AccountDeleteAction(this.app);
    }

    @Test
    public void deleteAccount() throws AccountNotFoundException, TransactionNotFoundException {
        var command = new ExpertCommand("account delete -a account", 2);

        var res = this.action.act(command);

        assertThat(res, Matchers.instanceOf(AccountDeleteResult.class));
        assertThat(this.app.accountDeleteCommand.accountAcronym(), Matchers.is("account"));
    }

    @Test
    public void deleteAccountMissingArgument() throws AccountNotFoundException, TransactionNotFoundException {
        var command = new ExpertCommand("account delete", 2);

        var res = this.action.act(command);

        assertThat(res, Matchers.instanceOf(AccountHelpResult.class));
    }
}
