package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.domain.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class AccountAllActionTest {

    public AccountIOApplicationTestImplementation app;
    public AccountAllAction action;

    @BeforeEach
    public void init() {
        this.app = new AccountIOApplicationTestImplementation();
        this.action = new AccountAllAction(this.app);
    }

    @Test
    public void allAccounts() throws BankNotFoundException, AccountNotFoundException {
        var command = new ExpertCommand("account all -a account", 2);

        var res = action.act(command);

        assertThat(res, Matchers.instanceOf(AccountAllResult.class));
        assertThat(this.app.accountGetQuery.bankAcronym(), Matchers.is("account"));
    }

    @Test
    public void allAccountMissingAgrument() throws BankNotFoundException, AccountNotFoundException {
        var command = new ExpertCommand("account all", 2);

        var res = action.act(command);

        assertThat(res, Matchers.instanceOf(AccountHelpResult.class));
    }
}
