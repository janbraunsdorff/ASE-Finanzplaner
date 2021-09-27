package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

class BankAllActionTest {

    @Test
    public void bankAll() throws BankNotFoundException {
        var app = new BankApplicationTestImplementation();
        var action = new BankAllAction(app);
        var command = new ExpertCommand("bank all", 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankAllResult.class));
    }
}
