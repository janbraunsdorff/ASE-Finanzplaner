package de.janbraunsdorff.ase.layer.presentation.console.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.action.Result;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

class AccountAddActionTest {
    @Test
    public void createAccount() throws BankNotFoundException, AcronymAlreadyExistsException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAddAction action = new AccountAddAction(app);
        Result act = action.act("account add -na name -nr number -ac acronym -a bankAc");

        assertThat(act, instanceOf(AccountAddResult.class));
        assertThat(app.createCommand.getName(), is("name"));
        assertThat(app.createCommand.getNumber(), is("number"));
        assertThat(app.createCommand.getAcronym(), is("acronym"));
        assertThat(app.createCommand.getBank(), is("bankAc"));
    }

    @Test
    public void createAccountIncompleteStatement() throws BankNotFoundException, AcronymAlreadyExistsException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAddAction action = new AccountAddAction(app);
        assertThat(action.act("account add -na name -nr number -ac acronym -a"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -na name -nr number -ac acronym"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -na name -nr number  -a bankAc"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -na name -nr number -ac -a bankAc"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -na name -ac acronym -a bankAc"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -na name -nr -ac acronym -a bankAc"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -na -nr number -ac acronym -a bankAc"), instanceOf(AccountHelpResult.class));
        assertThat(action.act("account add -nr number -ac acronym -a bankAc"), instanceOf(AccountHelpResult.class));

    }
}
