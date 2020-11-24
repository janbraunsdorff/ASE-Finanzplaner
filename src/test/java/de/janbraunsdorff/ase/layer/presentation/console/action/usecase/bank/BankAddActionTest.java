package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankAddActionTest {
    @Test
    public void addBank() throws AcronymAlreadyExistsException {
        BankApplicationTest service = new BankApplicationTest();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -a acronym -n name");
        assertThat(act, Matchers.instanceOf(BankAddResult.class));
        assertThat(act, Matchers.instanceOf(Result.class));

        assertThat(service.createCommand.getAcronym(), Matchers.is("acronym"));
        assertThat(service.createCommand.getName(), Matchers.is("name"));
    }


    @Test
    public void addBankIncompleteStatement() throws AcronymAlreadyExistsException {
        BankApplicationTest service = new BankApplicationTest();
        BankAddAction action = new BankAddAction(service);

        Result act = action.act("bank add -a acronym -n");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act("bank add -a acronym");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act("bank add -a -n name");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act("bank add -n name");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));


        act = action.act("bank add");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
