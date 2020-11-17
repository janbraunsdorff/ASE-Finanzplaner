package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AccountNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountDeleteResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AccountDeleteActionTest {

    @Test
    public void deleteAccountByAllGivenTags() throws AccountNotFoundException {
        ICrudAccountTestImpl repo = new ICrudAccountTestImpl();
        AccountDeleteAction service = new AccountDeleteAction(repo);

        Result act = service.act("bank delete -a acronym");

        assertThat(act, Matchers.instanceOf(AccountDeleteResult.class));
        assertThat(repo.deleteAcronym, is("acronym"));
    }

    @Test
    public void deleteAccountMissingTags() throws AccountNotFoundException {
        ICrudAccountTestImpl repo = new ICrudAccountTestImpl();
        AccountDeleteAction service = new AccountDeleteAction(repo);

        Result act = service.act("bank delete");

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }

    @Test
    public void deleteAccountByMissingValueOfAcronym() throws AccountNotFoundException {
        ICrudAccountTestImpl repo = new ICrudAccountTestImpl();
        AccountDeleteAction service = new AccountDeleteAction(repo);

        Result act = service.act("bank delete -a");

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }
}
