package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class PrintTransactionTest {
    @ParameterizedTest
    @CsvSource(value = {
            "print 022020:transaction print -a account -s 01022020 -e 29022020",
            "print 22020:transaction print -a account -s 01022020 -e 29022020",
            "print 012020 042020:transaction print -a account -s 01012020 -e 30042020",
            "print bla:transaction",
            "print:transaction",

    }, delimiter = ':')
    public void build(String cmd, String expected){
        var builder = new PrintTransaction();
        State sate = new State(Hierarchy.TRANSACTION, "bank", "account");
        ExpertCommand command = new ExpertCommand(cmd, 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is(expected));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
