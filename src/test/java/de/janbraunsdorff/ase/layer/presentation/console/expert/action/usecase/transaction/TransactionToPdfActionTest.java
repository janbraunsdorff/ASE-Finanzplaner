package de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;
import de.janbraunsdorff.ase.layer.presentation.console.expert.action.usecase.account.AccountIOApplicationTestImplementation;
import org.hamcrest.Matchers;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;

class TransactionToPdfActionTest {
    // @Test
    public void cratePdf() throws Exception {
        var tApp = new TransactionApplicationTestImplementation();
        var aApp = new AccountIOApplicationTestImplementation();
        var action = new TransactionToPdfAction(tApp, aApp);
        var command = new ExpertCommand("transaciton export -a account -s 01012020 -e 01022020", 2);

        Result res = action.act(command);

        assertThat(res, Matchers.instanceOf(TransactionToPdfResult.class));

        assertThat(tApp.transactionGetInIntervalQuery.get(0).account(), Matchers.contains("account"));
        assertThat(tApp.transactionGetInIntervalQuery.get(0).start(), Matchers.is(LocalDate.of(2020, 1, 1)));
        assertThat(tApp.transactionGetInIntervalQuery.get(0).end(), Matchers.is(LocalDate.of(2020, 1, 31)));

        assertThat(tApp.transactionGetInIntervalQuery.get(1).account(), Matchers.contains("account"));
        assertThat(tApp.transactionGetInIntervalQuery.get(1).start(), Matchers.is(LocalDate.of(0, 1, 1)));
        assertThat(tApp.transactionGetInIntervalQuery.get(1).end(), Matchers.is(LocalDate.of(2019, 12, 31)));

        assertThat(aApp.accountGetByAcronymQuery.get(0).acronym(), Matchers.is("account"));
        assertThat(aApp.accountGetByAcronymQuery.get(1).acronym(), Matchers.is(""));
    }
}
