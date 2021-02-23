package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;

class AccountHelpActionTest {

    @Test
    public void getAccountHelp(){
        var app = new AccountHelpAction();
        Result act = app.act(null);
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }
}
