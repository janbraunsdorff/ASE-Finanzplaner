package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;


import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

class BankAddActionTest {

    private BankApplicationTestImplementation app;
    private BankAddAction action;

    @BeforeEach
    public void init() {
        this.app = new BankApplicationTestImplementation();
        this.action = new BankAddAction(app);
    }

    @DisplayName("addBank")
    @Test
    public void addBank() throws AcronymAlreadyExistsException {
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
        var command = new ExpertCommand(commandString, 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
