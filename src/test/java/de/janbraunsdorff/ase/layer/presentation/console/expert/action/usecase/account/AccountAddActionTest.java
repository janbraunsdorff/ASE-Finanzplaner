package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.janbraunsdorff.ase.layer.domain.AcronymAlreadyExistsException;
import de.janbraunsdorff.ase.layer.domain.BankNotFoundException;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

class AccountAddActionTest {

    public AccountIOApplicationTestImplementation app;
    public AccountAddAction action;

    @BeforeEach
    public void init() {
        this.app = new AccountIOApplicationTestImplementation();
        this.action = new AccountAddAction(this.app);
    }

    @Test
    public void addAccount() throws BankNotFoundException, AcronymAlreadyExistsException {
        var command = new ExpertCommand("account add -na name -nr number -ac acronym -a bank", 2);
        Result act = this.action.act(command);

        assertThat(act, Matchers.instanceOf(AccountAddResult.class));
        assertThat(this.app.accountCreateCommand.acronym(), Matchers.is("acronym"));
        assertThat(this.app.accountCreateCommand.bank(), Matchers.is("bank"));
        assertThat(this.app.accountCreateCommand.number(), Matchers.is("number"));
        assertThat(this.app.accountCreateCommand.name(), Matchers.is("name"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "account add -na name -nr number -ac acronym",
            "account add -na name -nr number -a bank",
            "account add -na name -ac acronym -a bank",
            "account add -nr number -ac acronym -a bank",
    })
    public void addAccountWithMissingArgument(String commandString) throws AcronymAlreadyExistsException, BankNotFoundException {
        var command = new ExpertCommand(commandString, 2);
        Result act = this.action.act(command);

        assertThat(act, Matchers.instanceOf(AccountHelpResult.class));
    }
}
