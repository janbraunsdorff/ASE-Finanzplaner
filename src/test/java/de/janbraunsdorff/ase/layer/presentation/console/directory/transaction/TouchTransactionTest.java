package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;


class TouchTransactionTest {

   @ParameterizedTest
   @CsvSource(value = {
           "touch -val 20,70 -thp thp -dat 20.12.2021 -cat cat -con: transaction add -a account -val 20,70 -thp thp -dat 20.12.2021 -cat cat -con",
           "touch -val 20,70 -thp thp -dat 20.12.2021 -cat cat: transaction add -a account -val 20,70 -thp thp -dat 20.12.2021 -cat cat",

           "touch -val 20,70 -thp thp -dat 20.12.2021: transaction",
           "touch -val 20,70 -thp thp -cat cat: transaction",
           "touch -val 20,70 -dat 20.12.2021 -cat cat: transaction",
           "touch -thp thp -dat 20.12.2021 -cat cat: transaction",
   }, delimiter = ':')
    public void build(String command, String exp){
        var state = new State(Hierarchy.TRANSACTION, "bank", "account");
        var cmd = new ExpertCommand(command, 1);
        var action = new TouchTransaction();
       OverlayCommand res = action.build(state, cmd);

       assertThat(res.transition(), Matchers.is(StateTransition.STAY));
       assertThat(res.command().getInput(), Matchers.is(exp));
   }
}
