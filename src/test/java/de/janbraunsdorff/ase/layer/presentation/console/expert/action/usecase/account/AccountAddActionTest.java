package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

class AccountAddActionTest {
    @Test
    public void createAccount() throws BankNotFoundException, AcronymAlreadyExistsException {
        AccountTestApplication app = new AccountTestApplication();
        AccountAddAction action = new AccountAddAction(app);
        Result act = action.act(new Command("account add -na name -nr number -ac acronym -a bankAc",2));

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
        assertThat(action.act(new Command("account add -na name -nr number -ac acronym -a",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -na name -nr number -ac acronym",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -na name -nr number  -a bankAc",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -na name -nr number -ac -a bankAc",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -na name -ac acronym -a bankAc",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -na name -nr -ac acronym -a bankAc",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -na -nr number -ac acronym -a bankAc",2)), instanceOf(AccountHelpResult.class));
        assertThat(action.act(new Command("account add -nr number -ac acronym -a bankAc",2)), instanceOf(AccountHelpResult.class));

    }
}
