package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AccountAllActionTest {

    @Test
    public void getAccountWithAllTags() throws BankNotFoundExecption {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAllAction service = new AccountAllAction(repo);

        Result act = service.act("account get -a acronym");

        assertThat(act, Matchers.instanceOf(AccountResult.class));
        assertThat(repo.getAcronym, is("acronym"));
    }

    @Test
    public void getAccountWithValueMissing() throws BankNotFoundExecption {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAllAction service = new AccountAllAction(repo);

        Result act = service.act("account get -a");

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }

    @Test
    public void getAccountWithMissingTag() throws BankNotFoundExecption {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAllAction service = new AccountAllAction(repo);

        Result act = service.act("account get");

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }
}

