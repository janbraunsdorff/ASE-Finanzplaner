package de.janbraunsdorff.ase.layer.presentation.console.actions.account;

import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.crud.repository.exceptions.BankNotFoundExecption;
import de.janbraunsdorff.ase.layer.presentation.console.result.Result;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountHelpResult;
import de.janbraunsdorff.ase.layer.presentation.console.result.account.AccountNewResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AccountAddActionTest {
    @Test
    public void createAccountWithAllTags() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -nr number -ac acronym -a bank");
        assertThat(act, Matchers.instanceOf(AccountNewResult.class));
        assertThat(repo.addBankId, is("bank"));
        assertThat(repo.addAccount.getAcronym(), is("acronym"));
        assertThat(repo.addAccount.getName(), is("name"));
        assertThat(repo.addAccount.getNumber(), is("number"));
    }

    @Test
    public void createAccountWithMissingBank() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -nr number -ac acronym");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingAcronym() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -nr number -a bank");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingNumber() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -ac acronym -a bank");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingName() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -nr number -ac acronym -a bank");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingBankValue() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -nr number -ac acronym -a");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingAcronymValue() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -nr number -ac -a bank");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingNumberValue() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na name -nr -ac acronym -a bank");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }

    @Test
    public void createAccountWithMissingNameValue() throws BankNotFoundExecption, AcronymAlreadyExistsException {
        ICurdAccountTestImpl repo = new ICurdAccountTestImpl();
        AccountAddAction service = new AccountAddAction(repo);

        Result act = service.act("bank add -na -nr number -ac acronym -a bank");
        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));

    }
}
