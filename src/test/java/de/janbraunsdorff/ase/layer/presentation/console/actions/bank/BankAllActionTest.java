package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankAllActionTest {
    @Test
    public void getAllBanks() throws BankNotFoundException {
        BankApplicationTest service = new BankApplicationTest();
        BankAllAction action = new BankAllAction(service);

        Result act = action.act("");

        assertThat(act, Matchers.instanceOf(BankResult.class));
        assertThat(act, Matchers.instanceOf(Result.class));
        assertThat(service.getWasCalled, Matchers.is(true));
    }
}
