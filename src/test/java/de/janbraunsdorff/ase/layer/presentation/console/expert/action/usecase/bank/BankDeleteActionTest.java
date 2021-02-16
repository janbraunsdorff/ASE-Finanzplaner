package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;

class BankDeleteActionTest {
    @DisplayName("addBank")
    @Test
    public void addBank() {
        var app = new BankApplicationTestImplementation();
        var action = new BankDeleteAction(app);
        var command = new ExpertCommand("bank delete -a acronym", 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankDeleteResult.class));

        // check call
        assertThat(app.acronym, Matchers.is("acronym"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "bank delete",
    })
    public void missingParameterAddBank(String commandString) throws AcronymAlreadyExistsException {
        var app = new BankApplicationTestImplementation();
        var action = new BankDeleteAction(app);
        var command = new ExpertCommand(commandString, 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
