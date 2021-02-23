package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class TransactionGroupActionTest {

    private TransactionApplicationTestImplementation service;
    private TransactionGroupAction action;

    @BeforeEach
    public void init(){
        this.service = new TransactionApplicationTestImplementation();
        this.action = new TransactionGroupAction(service);
    }

    @Test
    public void groupTransactions() throws Exception {
        var command = new ExpertCommand("transaction group -a test", 2);
        var res = action.act(command);

        assertThat(res, Matchers.instanceOf(TransactionGroupResult.class));

        assertThat(service.groupMonthly.getAccount(), Matchers.is("test"));
    }

    @Test
    public void groupTransactionsMissingAccount() throws Exception {
        var command = new ExpertCommand("transaction group", 2);
        var res = action.act(command);

        assertThat(res, Matchers.instanceOf(TransactionHelpResult.class));
    }
}
