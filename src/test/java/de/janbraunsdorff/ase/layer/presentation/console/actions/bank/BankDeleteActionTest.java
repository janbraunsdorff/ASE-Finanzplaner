package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankDeleteActionTest {
    @Test
    public void deleteBankAction() {
        BankApplicationTest service = new BankApplicationTest();
        BankDeleteAction action = new BankDeleteAction(service);

        Result act = action.act("bank delete -a acronym");

        assertThat(act, Matchers.instanceOf(BankDeleteResult.class));
        assertThat(service.deleteCommand.getAcronym(), Matchers.is("acronym"));

    }

    @Test
    public void deleteBankActionWithIncompleteCommand() {
        BankApplicationTest service = new BankApplicationTest();
        BankDeleteAction action = new BankDeleteAction(service);

        Result act = action.act("bank delete -a");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

        act = action.act("bank delete");
        assertThat(act, Matchers.instanceOf(BankHelpResult.class));

    }
}
