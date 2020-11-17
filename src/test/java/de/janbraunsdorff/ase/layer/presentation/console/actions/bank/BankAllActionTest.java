package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAllActionTest {
    @Test
    public void getAllBanks() throws BankNotFoundExecption {
        ICrudBankTestImpl repo = new ICrudBankTestImpl();
        BankAllAction service = new BankAllAction(repo);

        Result res = service.act("");

        assertThat(res, Matchers.instanceOf(BankResult.class));
        assertTrue(repo.isGetCalled);
    }
}
