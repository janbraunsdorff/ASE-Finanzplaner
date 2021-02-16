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

class BankAddActionTest {

    @DisplayName("addBank")
    @Test
    public void addBank() throws AcronymAlreadyExistsException {
        var app = new BankApplicationTestImplementation();
        var action = new BankAddAction(app);
        var command = new ExpertCommand("bank add -a acronym -n name -t type", 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankAddResult.class));

        // check call
        assertThat(app.name, Matchers.is("name"));
        assertThat(app.acronym, Matchers.is("acronym"));
        assertThat(app.type, Matchers.is("type"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "bank add -n name -t types",
            "bank add -a acronym -t type",
            "bank add -a acronym -n name"
    })
    public void missingParameterAddBank(String commandString) throws AcronymAlreadyExistsException {
        var app = new BankApplicationTestImplementation();
        var action = new BankAddAction(app);
        var command = new ExpertCommand(commandString, 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
