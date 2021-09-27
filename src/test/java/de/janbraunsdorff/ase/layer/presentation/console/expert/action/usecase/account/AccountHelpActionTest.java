package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

class AccountHelpActionTest {

    @Test
    public void getAccountHelp(){
        var app = new AccountHelpAction();
        Result act = app.act(null);
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }
}
