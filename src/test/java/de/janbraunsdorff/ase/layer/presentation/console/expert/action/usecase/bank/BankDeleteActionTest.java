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

class BankDeleteActionTest {

    public BankApplicationTestImplementation app;
    public BankDeleteAction action;

    @BeforeEach
    public void init(){
        this.app = new BankApplicationTestImplementation();
        this.action = new BankDeleteAction(app);
    }

    @DisplayName("addBank")
    @Test
    public void deleteBank() {
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
    public void missingParameterDeleteBank(String commandString) throws AcronymAlreadyExistsException {
        var command = new ExpertCommand(commandString, 2);

        Result act = action.act(command);

        // Check Return Type
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
