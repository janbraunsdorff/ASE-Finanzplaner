package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.bank.BankHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BankDeleteActionTest {
    @Test
    public void deleteBankWithTagAcronym() throws BankNotFoundExecption {
        ICrudBankTestImpl repo = new ICrudBankTestImpl();
        BankDeleteAction action = new BankDeleteAction(repo);

        Result act = action.act("bank delete -a acronym");

        assertThat(act, Matchers.instanceOf(BankDeleteResult.class));
        assertThat(repo.deleteAcronym, is("acronym"));
    }

    @Test
    public void deleteBankWithMissingTagAcronym() throws BankNotFoundExecption {
        ICrudBankTestImpl repo = new ICrudBankTestImpl();
        BankDeleteAction action = new BankDeleteAction(repo);

        Result act = action.act("bank delete");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }

    @Test
    public void deleteBankWithMissingValueOfAcronym() throws BankNotFoundExecption {
        ICrudBankTestImpl repo = new ICrudBankTestImpl();
        BankDeleteAction action = new BankDeleteAction(repo);

        Result act = action.act("bank delete -a");

        assertThat(act, Matchers.instanceOf(BankHelpResult.class));
    }
}
