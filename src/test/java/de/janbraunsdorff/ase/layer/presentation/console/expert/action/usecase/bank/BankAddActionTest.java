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
        BankApplicationTest service = new BankApplicationTest();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act(new ExpertCommand("bank add -a acronym -n name", 2));
        assertThat(act, Matchers.instanceOf(BankAddResult.class));
        assertThat(act, Matchers.instanceOf(Result.class));

        assertThat(service.createCommand.getAcronym(), Matchers.is("acronym"));
        assertThat(service.createCommand.getName(), Matchers.is("name"));
    }


    @Test
    public void addBankIncompleteStatement() throws AcronymAlreadyExistsException {
        BankApplicationTest service = new BankApplicationTest();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act(new ExpertCommand("bank add -a acronym -n", 2));
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act(new ExpertCommand("bank add -a acronym", 2));
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act(new ExpertCommand("bank add -a -n name", 2));
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act(new ExpertCommand("bank add -n name", 2));
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));


        act = action.act(new ExpertCommand("bank add", 2));
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
