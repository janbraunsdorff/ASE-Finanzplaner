package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankHelpActionTest {

    @Test
    public void helpBank() throws AcronymAlreadyExistsException {
        var action = new BankHelpAction();
        var command = new ExpertCommand("bank non", 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
