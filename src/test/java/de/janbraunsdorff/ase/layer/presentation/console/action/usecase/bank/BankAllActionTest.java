package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.bank;

import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class BankAllActionTest {
    @Test
    public void getAllBanks() throws BankNotFoundException {
        BankApplicationTest service = new BankApplicationTest();
        BankAllAction action = new BankAllAction(service);

        Result act = action.act("");

        assertThat(act, Matchers.instanceOf(BankAllResult.class));
        assertThat(act, Matchers.instanceOf(Result.class));
        assertThat(service.getWasCalled, Matchers.is(true));
    }
}
