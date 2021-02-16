package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.bank;


import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankAddActionTest {

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
}
