package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankDefaultActionTest {
    @Test
    public void bankDefaultReturnsBankHelp(){
        BankHelpAction action = new BankHelpAction();
        Result act = action.act(new ExpertCommand("", 0));

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
